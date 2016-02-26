package uk.nhs.itk.ciao;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * This is a unit test for the core CIAO camel functionality available in
 * all CIAO CIPs.
 * @author Adam Hatherly
 */
public class TestCIP extends CamelTestSupport {

	/**
	 * Use the method provided by CamelTestSupport to initialise our context
	 */
	@Override
	protected CamelContext createCamelContext() throws Exception {
		CIP cip = new CIP();
		String[] args = new String[] {};
		String defaultConfigFile = "testCIP.properties";
		@SuppressWarnings("deprecation")
		CamelContext context = cip.init(args, defaultConfigFile);
		return context;
	}
	
	/**
	 * Initialise the route we want to test
	 */
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new Route();
	}

	/**
	 * Unit test method - this unit test is testing the end-to-end initialisation and
	 * use of a CIP, and the use of ciao-configuration for config values.
	 * @throws Exception
	 */
	@Test
	public void testSubmitGetRequest() throws Exception {	
		
		// Expected results
		MockEndpoint mock = getMockEndpoint("mock:assert");
		mock.expectedBodiesReceived("12345");
		
		// Set up notifier to tell us once the matching responses have been received
		NotifyBuilder notify = new NotifyBuilder(context).fromRoute("testRouteHTTP")
					.whenReceivedSatisfied(mock).create();
		
		Thread.sleep(500);
		// Submit request
		template.sendBodyAndHeader("jetty:http://0.0.0.0:8765/test",
						ExchangePattern.InOut, "", Exchange.HTTP_METHOD, "GET");
		
		boolean matches = notify.matches(5, TimeUnit.SECONDS);
		assertTrue(matches);
	}
}
