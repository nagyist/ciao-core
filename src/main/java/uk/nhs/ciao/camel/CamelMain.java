package uk.nhs.ciao.camel;

import org.apache.camel.spring.Main;
import org.apache.camel.util.IOHelper;
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
public class CamelMain extends Main {
	private static CamelMain instance;
	
	/**
	 * Starts a new camel application
	 * 
	 * @param config The CIAO configuration used by the application
	 * @param args The application arguments
	 * @throws Exception If the application could not be started
	 */
	public static CamelMain startApplication(final CamelApplication application) throws Exception {
        final CamelMain main = new CamelMain(application);
        Main.instance = main;
        instance = main;
        main.enableHangupSupport();
        main.run(application.getArguments());

        return main;
    }
	
	/**
	 * The system-wide application instance
	 * 
	 * @see Main#getInstance()
	 */
	public static CamelMain getInstance() {
		return instance;
	}
	
	private final CamelApplication application;

	/**
	 * Creates a new main to run the specified application
	 */
	public CamelMain(final CamelApplication application) {
		super();
		this.application = application;
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
		final AbstractApplicationContext parentContext = getOrCreateParentApplicationContext();
		parentContext.refresh();
		parentContext.start();
		
		super.doStart();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doStop() throws Exception {
		super.doStop();
		
		IOHelper.close(getParentApplicationContext());
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
