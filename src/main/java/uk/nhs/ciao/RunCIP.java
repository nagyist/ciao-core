package uk.nhs.ciao;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import uk.nhs.ciao.properties.CiaoPropertyResolver;

/** 
 * @author Adam Hatherly
 * 
 * @deprecated Can replace with spring config and {@link uk.nhs.ciao.camel.CamelApplication}
 */
public abstract class RunCIP {
	
	@SuppressWarnings("javadoc")
	public CamelContext init(String[] args, String defaultConfigFile) throws Exception {
		try {
			// Create a new JNDI context as our camel registry
			JndiContext jndi = new JndiContext();
			
			// Initialise CIP config
			PropertiesComponent pc = CiaoPropertyResolver
						.createPropertiesComponent(defaultConfigFile, args);
			
			// Add bean mappings
			populateCamelRegistry(jndi);
			
			CamelContext context = new DefaultCamelContext(jndi);		
			// Add our custom property resolver
			context.addComponent("properties", pc);			
			
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
