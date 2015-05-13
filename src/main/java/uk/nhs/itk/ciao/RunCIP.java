package uk.nhs.itk.ciao;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.nhs.itk.ciao.properties.CiaoPropertyResolver;

/** 
 * @author Adam Hatherly
 */
public abstract class RunCIP {
	
	private static Logger logger = LoggerFactory.getLogger(CIP.class);
	
	public CamelContext init(String[] args, String defaultConfigFile) throws Exception {
		try {
			// Initialise CIP config
			//Properties defaultConfig = loadDefaultConfig();
			//String version = defaultConfig.get("cip.version").toString();
			//String cipName = defaultConfig.get("cip.name").toString();
			//CIAOConfig cipConfig = new CIAOConfig(args, cipName, version, defaultConfig);
			
			// Create a new JNDI context as our camel registry
			JndiContext jndi = new JndiContext();
			// Add bean mappings
			populateCamelRegistry(jndi);
			
			CamelContext context = new DefaultCamelContext(jndi);		
			
			// Add our custom property resolver
			CiaoPropertyResolver.createPropertiesComponent(defaultConfigFile, args, context);
			
			// Set some other global values
			context.setStreamCaching(true);
			context.setTracing(true);
			context.getProperties().put(Exchange.LOG_DEBUG_BODY_STREAMS, "true");
			return context;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void startCamel(CamelContext context) throws Exception {
		context.start();
	}
	
	/**
	 * Register our custom beans using JNDI (in an OSGi deployment this is configured in beans.xml) 
	 * @param jndi
	 * @throws Exception
	 */
	protected void populateCamelRegistry(JndiContext jndi) throws Exception {
		
	};
	
}
