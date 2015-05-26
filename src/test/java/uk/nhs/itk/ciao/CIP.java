package uk.nhs.itk.ciao;

import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.util.jndi.JndiContext;

import uk.nhs.ciao.RunCIP;

/**
 * This is a sample CIP implementation for the purposes of unit testing.
 * @author Adam Hatherly
 */
public class CIP extends RunCIP {
	
	/**
	 * This is an example of how you can initialise your CIP as a simple
	 * java process.
	 * @param args Command line arguments
	 * @throws Exception If initialisation fails
	 */
	public static void main(String[] args) throws Exception {
		new CIP().startCIP(args);
	}
	
	/**
	 * This uses methods in the ciao-core to create our camel context
	 * and initialise the configuration so it is available in routes.
	 * @param args Command line arguments (passed to ciao-configuration to allow specific etcd or file configuration to be specified)
	 * @throws Exception If initialisation fails
	 */
	public void startCIP(String[] args) throws Exception {
		CamelContext context = super.init(args, "testCIP.properties");
		context.addRoutes(new Route());
		super.startCamel(context);
	}

	/**
	 * Overriding this method allows us to register custom beans for use in routes.
	 * @param JndiContext This is a JNDI repository to use for initialising the camel registry.
	 * @throws NamingException
	 */
	@Override
	protected void populateCamelRegistry(JndiContext jndi)
			throws NamingException {
		jndi.bind("loggingBean", new LoggingBean());
	}
}
