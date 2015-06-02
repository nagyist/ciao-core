package uk.nhs.ciao.camel;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.camel.spring.Main;
import org.apache.camel.util.IOHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import uk.nhs.ciao.exceptions.CIAOConfigurationException;

/**
 * Main runner for starting a Camel spring application via spring
 * <p>
 * Extends the default {@link Main} class to support creating a parent
 * application context (specified by the application)
 * 
 * @see Main
 * @see CamelApplication#createParentApplicationContext()
 */
public class CamelApplicationRunner extends Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(CamelApplicationRunner.class);
	
	/**
	 * The default path of the spring application context resource to load
	 */
	public static final String DEFAULT_APPLICATION_CONTEXT_URI = "META-INF/spring/beans.xml";
	
	private static volatile CamelApplicationRunner instance;
	
	/**
	 * Runs the specified camel application
	 * <p>
	 * The application will run with hang-up support enabled
	 * <p>
	 * This method blocks the calling thread until the application has terminated
	 * 
	 * @param application The application to run
	 * @throws Exception If the application could not be started
	 * @see #getInstance()
	 */
	public static void runApplication(final CamelApplication application) throws Exception {
        try {
			final CamelApplicationRunner main = new CamelApplicationRunner(application);
	        Main.instance = main;
	        instance = main;
        	main.enableHangupSupport();
	        main.run(application.getArguments());
        } finally {
        	Main.instance = null;
        	instance = null;
        }
    }
	
	/**
	 * Runs the specified camel application via an executor
	 * <p>
	 * The application will run without hang-up support enabled
	 * <p>
	 * This method will return once the application has started unless an exception is thrown. The returned
	 * AsyncExecution can be used to examine/stop the application or to examine exception thrown after
	 * the application started
	 * 
	 * @param application The application to run
	 * @param executorService The executorService to use when running the application
	 * @return The async execution associated with the running application
	 */
	public static AsyncExecution runApplication(final CamelApplication application,
			final ExecutorService executorService) throws Exception {
		// A reference to the runner - all construction is handled in the worker thread
		final AtomicReference<CamelApplicationRunner> runnerRef = new AtomicReference<CamelApplicationRunner>();
		
		// If an exception is thrown - marshal it into the calling thread
		final AtomicReference<Throwable> causeRef = new AtomicReference<Throwable>();
		
		// Block until either an exception is thrown or the application has started
		final CountDownLatch latch = new CountDownLatch(1);
		
		// Watches for application started
		final LifecycleListener lifecycleListener = new LifecycleListener() {
			public void onStarted() {
				super.onStarted();
				latch.countDown();
			};
		};
		
		// Task to run the application
		final Callable<Void> task = new Callable<Void>() {			
			@Override
			public Void call() throws Exception {
				try {
					final CamelApplicationRunner main = new CamelApplicationRunner(application,
							lifecycleListener);
					runnerRef.set(main);
			        Main.instance = main;
			        instance = main;
			        main.run(application.getArguments());
		        } catch (Throwable e) {
		        	LOGGER.error("Exception while running CamelApplication", e);
		        	causeRef.set(e);
		        	latch.countDown();
		        	
		        	throw exception(e);
		        }
		        finally {
		        	Main.instance = null;
		        	instance = null;
		        }
				
				return null;
			}
		};
		
		// Start the application
		final Future<Void> future = executorService.submit(task);
		
		// Wait until running or failure (whichever comes first)
		latch.await();
		
		final Throwable cause = causeRef.get();
		if (cause != null) {
			throw exception(cause);
		}

		return new AsyncExecution(runnerRef.get(), future);
	}
	
	/**
	 * Represents the execution of a camel application in another thread
	 */
	public static class AsyncExecution {
		private final CamelApplicationRunner runner;
		private final Future<Void> future;
		
		private AsyncExecution(final CamelApplicationRunner runner, final Future<Void> future) {
			this.runner = runner;
			this.future = future;
		}
		
		/**
		 * The application runner
		 */
		public CamelApplicationRunner getRunner() {
			return runner;
		}
		
		/**
		 * The future associated with the aync execution.
		 * <p>
		 * The future can be examined to see whether the application is running,
		 * wait for termination, or retrieve application excceptions that were thrown
		 * during the run
		 */
		public Future<Void> getFuture() {
			return future;
		}
	}
	
	// a little syntactic sugar to simplify re-throwing Throwables
	private static Exception exception(final Throwable throwable) {
		if (throwable instanceof Exception) {
			return (Exception)throwable;
		} else {
			throw (Error)throwable;
		}
	}
	
	/**
	 * Listens to lifecycle events while running an application
	 */
	public static class LifecycleListener {
		/**
		 * Invoked when the application is starting
		 */
		public void onStarting() {
			// Default is NOOP - free for subclasses to override
		}
		
		/**
		 * Invoked when the application has started
		 */
		public void onStarted() {
			// Default is NOOP - free for subclasses to override
		}
		
		/**
		 * Invoked when the application is stopping
		 */
		public void onStopping() {
			// Default is NOOP - free for subclasses to override
		}
		
		/**
		 * Invoked when the application has stopped
		 */
		public void onStopped() {
			// Default is NOOP - free for subclasses to override
		}
	}
	
	/**
	 * The system-wide application instance
	 * 
	 * @see Main#getInstance()
	 */
	public static CamelApplicationRunner getInstance() {
		return instance;
	}
	
	private final CamelApplication application;
	private final LifecycleListener lifecycleListener;

	/**
	 * Creates a new main to run the specified application
	 */
	public CamelApplicationRunner(final CamelApplication application, final LifecycleListener lifecycleListener) {
		super();
		this.application = application;
		this.lifecycleListener = lifecycleListener;
		
		// The default wild-card expression can make the loading of a main file
		// unpredictable (especially with a test classpath)
		setApplicationContextUri(DEFAULT_APPLICATION_CONTEXT_URI);
	}
	
	/**
	 * Creates a new main to run the specified application
	 */
	public CamelApplicationRunner(final CamelApplication application) {
		this(application, new LifecycleListener());
	}
	
	/**
	 * The contained application
	 */
	public CamelApplication getApplication() {
		return application;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doStart() throws Exception {
		lifecycleListener.onStarting();
		
		final AbstractApplicationContext parentContext = getOrCreateParentApplicationContext();
		parentContext.refresh();
		parentContext.start();
		
		super.doStart();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void afterStart() throws Exception {
        super.afterStart();
        
        lifecycleListener.onStarted();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    protected void beforeStop() throws Exception {
		lifecycleListener.onStopping();
		
        super.beforeStop();
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doStop() throws Exception {
		try {
			super.doStop();
		} finally {
			IOHelper.close(getParentApplicationContext());		
		}
		
		lifecycleListener.onStopped();
	}

	/**
	 * Returns the parent application context, creating a new instance if required.
	 */
	private AbstractApplicationContext getOrCreateParentApplicationContext() throws CIAOConfigurationException {
		AbstractApplicationContext parentContext = getParentApplicationContext();
		
		if (parentContext == null) {
			parentContext = application.createParentApplicationContext();
			setParentApplicationContext(parentContext);
		}
		
		return parentContext;
	}
}
