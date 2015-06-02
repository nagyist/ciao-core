package uk.nhs.ciao.camel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
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
	 * Runs the specified camel application in a new thread
	 * <p>
	 * The application will run without hang-up support enabled
	 * <p>
	 * This method will return once the application has started unless an exception is thrown. The CamelApplication
	 * instance can be found via {@link #getInstance()} after this method successfully completes.
	 * 
	 * @param application The application to run
	 * @param threadFactory The thread factory used to spawn a new thread
	 * @return The thread that was created
	 */
	public static Thread runApplication(final CamelApplication application, final ThreadFactory threadFactory) throws Exception {
		// Block until either an exception is thrown or the application has started
		final CountDownLatch latch = new CountDownLatch(1);
		
		// If an exception is thrown - marshal it into the calling thread
		final AtomicReference<Throwable> causeReference = new AtomicReference<Throwable>();
		
		// Watches for application started
		final LifecycleListener lifecycleListener = new LifecycleListener() {
			public void onStarted() {
				super.onStarted();
				latch.countDown();
			};
		};
		
		// Task to run the application
		final Runnable runnable = new Runnable() {			
			@Override
			public void run() {
				try {
					final CamelApplicationRunner main = new CamelApplicationRunner(application, lifecycleListener);
			        Main.instance = main;
			        instance = main;
			        main.run(application.getArguments());
		        } catch (Throwable e) {
		        	LOGGER.error("Exception while running CamelApplication", e);
		        	causeReference.set(e);
		        	latch.countDown();
		        }
		        finally {
		        	Main.instance = null;
		        	instance = null;
		        }
			}
		};
		
		// Start the application
		final Thread thread = threadFactory.newThread(runnable);
		thread.start();
		
		// Wait until running or failure (whichever comes first)
		latch.await();
		
		final Throwable cause = causeReference.get();
		if (cause == null) {
			return thread;
		} else if (cause instanceof Exception) {
			throw (Exception)cause;
		} else {
			throw (Error)cause;
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
