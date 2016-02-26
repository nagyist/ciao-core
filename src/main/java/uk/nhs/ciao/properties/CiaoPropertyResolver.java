package uk.nhs.ciao.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.properties.PropertiesResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.nhs.ciao.configuration.CIAOConfig;
import uk.nhs.ciao.exceptions.CIAOConfigurationException;

/**
 * @deprecated see {@link uk.nhs.ciao.spring.CiaoParentApplicationContextFactory} and standard spring<->camel bindings
 */
@Deprecated
public final class CiaoPropertyResolver 
		implements PropertiesResolver {
	
	// We can only have one set of config active when we are using the camel property placeholders
	private static CIAOConfig cipConfig = null;
	private static String version = null;
	private static String cipName = null;
	private static Logger logger = LoggerFactory.getLogger(CiaoPropertyResolver.class);
	
	public Properties resolveProperties(CamelContext arg0, boolean arg1,
			String... arg2) throws Exception {
		logger.info("PropertiesResolver - resolveProperties - values:" + cipConfig.toString());
		return cipConfig.getAllProperties();
	}
	
	/**
	 * @param defaultConfigFileName
	 * @param args
	 * @return PropertiesComponent
	 * @throws CIAOConfigurationException
	 */
	public static PropertiesComponent createPropertiesComponent(String defaultConfigFileName, String[] args) throws CIAOConfigurationException {
		if (cipConfig != null) {
			throw new CIAOConfigurationException("Attempting to initialise CIP configuration more than once - this is not allowed");
		} else {
			Properties defaultConfig = loadDefaultConfig(defaultConfigFileName);
			version = defaultConfig.get("cip.version").toString();
			cipName = defaultConfig.get("cip.name").toString();
			cipConfig = new CIAOConfig(args, cipName, version, defaultConfig);
			logger.info("Initialised CIP configuration");
			logger.info("CIP config values: {}", cipConfig.toString());
			PropertiesComponent pc = new PropertiesComponent();
			pc.setPropertiesResolver(new CiaoPropertyResolver());
			//pc.addFunction(new CiaoPropertyResolver());
			pc.setLocation("DUMMYLOCATION");
			return pc;
		}
	}
	
	private static Properties loadDefaultConfig(String defaultConfigFileName) {
		InputStream in = null;
		Properties defaultProperties = new Properties();
        try {
        	in = CiaoPropertyResolver.class.getClassLoader().getResourceAsStream(defaultConfigFileName);
            if (in != null) {
            	defaultProperties.load(in);
            	in.close();
            }
        } catch (Exception ex) {
       		logger.error("Default config not found: " + defaultConfigFileName, ex);
       		return null;
        } finally {
            try {
                if (in != null) {
                	in.close();
                }
            } catch (IOException ex) {
            }
        }
        return defaultProperties;
	}
	
	/**
	 * Convenience method to directly access a config value from CIP code
	 * @param key
	 * @return Property value
	 * @throws CIAOConfigurationException 
	 */
	public static String getConfigValue(String key) throws CIAOConfigurationException {
		if (cipConfig == null) {
			throw new CIAOConfigurationException("CIAO Configuration has not yet been initialised!");
		} else {
			return cipConfig.getConfigValue(key);
		}
	}
}