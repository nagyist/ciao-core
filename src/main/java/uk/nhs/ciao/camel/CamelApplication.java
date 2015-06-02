package uk.nhs.ciao.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.StaticApplicationContext;

import uk.nhs.ciao.configuration.CIAOConfig;
import uk.nhs.ciao.exceptions.CIAOConfigurationException;
import uk.nhs.ciao.properties.CIAOConfigFactory;
import uk.nhs.ciao.spring.CiaoParentApplicationContextFactory;

/**
 * A CIAO camel application
 * <p>
 * Camel is initialised (via spring) and the specified CIAO properties
 * are registered and made available via Spring environment properties.
 * <p>
 * To make the CIAO/spring properties available to camel - an instance
 * of {@link org.apache.camel.spring.spi.BridgePropertyPlaceholderConfigurer}
 * should be registered in the XML configuration.
 * 
 * @see CamelApplicationRunner
 */
public class CamelApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CamelApplication.class);
	
	/**
	 * The default path of the spring application context resource to load
	 */
	public static final String DEFAULT_APPLICATION_CONTEXT_URI = "META-INF/spring/beans.xml";
	
	/**
	 * Gets the CIAOConfig from the specified camel context
	 * 
	 * @return The configuration stored in the context's registry
	 * @see CiaoParentApplicationContextFactory#PROPERTY_CIAO_CONFIG
	 */
	public static CIAOConfig getConfig(final CamelContext context) {
		return getConfig(context.getRegistry());
	}
	
	/**
	 * Gets the CIAOConfig from the specified camel registry
	 * 
	 * @return The configuration stored in the specified registry
	 * @see CiaoParentApplicationContextFactory#PROPERTY_CIAO_CONFIG
	 */
	public static CIAOConfig getConfig(final Registry registry) {
		return registry.lookupByNameAndType(CiaoParentApplicationContextFactory.PROPERTY_CIAO_CONFIG,
				CIAOConfig.class);
	}
	
	private final CIAOConfig config;
	private final String[] arguments;
	private String applicationContextUri = DEFAULT_APPLICATION_CONTEXT_URI;

	/**
	 * Creates a new application backed by the specified CIAO configuration
	 */
	public CamelApplication(final CIAOConfig config, final String... arguments) {
		super();
		this.config = config;
		this.arguments = arguments;
	}
	
	/**
	 * Creates a new camel application using the specified default configuration
	 * from the classpath
	 * 
	 * @param defaultConfigPath The location of the default configuration properties
	 * 			on the classpath
	 * @param args The application arguments
	 * @throws CIAOConfigurationException If the application could not be configured
	 * @see CIAOConfigFactory
	 */
	public CamelApplication(final String defaultConfigPath, final String... arguments) throws CIAOConfigurationException {
		this.config = CIAOConfigFactory.getCIAOConfigFromClasspath(defaultConfigPath, arguments);
		this.arguments = arguments;
		LOGGER.info("Initialised CIP configuration");
		LOGGER.info("CIP config values: {}", config);
	}
	
	/**
	 * The config used by this application
	 */
	public CIAOConfig getCIAOConfig() {
		return config;
	}
	
	/**
	 * The application arguments
	 */
	public String[] getArguments() {
		return arguments;
	}
	
	/**
	 * The path of the spring application context config for this application
	 * 
	 * @see #DEFAULT_APPLICATION_CONTEXT_URI
	 */
	public String getApplicationContextUri() {
		return applicationContextUri;
	}
	
	/**
	 * Sets the path of the spring application context config for this application
	 * <p>
	 * protected access - intended for subclasses only
	 */
	protected void setApplicationContextUri(final String applicationContextUri) {
		this.applicationContextUri = applicationContextUri;
	}
	
	/**
	 * Create the parent application context for this application.
	 * <p>
	 * Any properties or beans specified on the parent context will be made available to
	 * the main application context.
	 * <p>
	 * Sub-classes can override this method to provide additional beans or properties
	 * on the parent context.
	 */
	protected StaticApplicationContext createParentApplicationContext() throws CIAOConfigurationException {
		return new CiaoParentApplicationContextFactory(config).createParentApplicationContext();
	}
}
