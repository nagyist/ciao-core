package uk.nhs.ciao.spring;

import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.env.PropertySource;

import uk.nhs.ciao.configuration.CIAOConfig;
import uk.nhs.ciao.exceptions.CIAOConfigurationException;

/**
 * A factory to create parent application contexts for a CIAO spring application.
 * <p>
 * When a context is created, it's environment is initialised by a backing {@link CIAOConfig}
 * instance. In addition to making the properties available via the environment the following
 * beans properties are registered:
 * <dl>
 * <dt>ciaoConfig</dt>
 * <dd>The backing CIAOConfig instance</dd>
 * <dt>ciaoProperties</dt>
 * <dd>A Properties instance obtained via {@link CIAOConfig#getAllProperties()}</dd>
 * </dl>
 * <p>
 * A configured parent context should be specified when creating the main CIAO application context.
 * This automatically makes all environment properties and registered beans available to the main
 * context. Crucially, it also means that the properties are loaded and made available
 * <strong>before</strong> any of the main context is loaded. This means that CIAO properties
 * can be referenced anywhere in the main XML config - including in import statements (which
 * would otherwise be impossible).
 */
public class CiaoParentApplicationContextFactory {
	/**
	 * Property key for the backing CIAOConfig instance
	 */
	public static final String PROPERTY_CIAO_CONFIG = "ciaoConfig";
	/**
	 * Property key for the Properties instance
	 */
	public static final String PROPERTY_CIAO_PROPERTIES = "ciaoProperties";
	
	private final CIAOConfig config;
	
	/**
	 * @param config
	 */
	public CiaoParentApplicationContextFactory(final CIAOConfig config) {
		this.config = config;
	}
	
	/**
	 * Creates a new CIAO parent application context
	 * 
	 * @return A CIAO parent application context
	 * @throws CIAOConfigurationException If the CIAO properties could not be loaded
	 */
	public StaticApplicationContext createParentApplicationContext() throws CIAOConfigurationException {
		final StaticApplicationContext parentContext = new StaticApplicationContext();
		
		registerEnvironmentPropertySource(parentContext);
		registerPropertiesBeans(parentContext);
		
		return parentContext;
	}
	
	/**
	 * Adds the CIAO properties as a spring environment property source
	 */
	private void registerEnvironmentPropertySource(final ConfigurableApplicationContext context) throws CIAOConfigurationException {
		final PropertySource<CIAOConfig> propertySource = new CIAOConfigPropertySource(PROPERTY_CIAO_CONFIG, config);
		context.getEnvironment().getPropertySources().addFirst(propertySource);
	}

	/**
	 * Adds the config and properties as standard spring beans
	 */
	private void registerPropertiesBeans(final StaticApplicationContext parentContext)
			throws CIAOConfigurationException {
		parentContext.registerBeanDefinition(PROPERTY_CIAO_CONFIG, SingletonFactoryBean.defineSingletonBean(
				CIAOConfig.class, config));
		parentContext.registerBeanDefinition(PROPERTY_CIAO_PROPERTIES, SingletonFactoryBean.defineSingletonBean(
				Properties.class, config.getAllProperties()));
	}
}
