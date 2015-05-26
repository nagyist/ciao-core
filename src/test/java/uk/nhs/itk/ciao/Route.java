package uk.nhs.itk.ciao;

import org.apache.camel.PropertyInject;

import uk.nhs.ciao.CIPRoutes;

/**
 * This is a sample CIP route for the purpose of unit testing.
 * @author Adam Hatherly
 */
public class Route extends CIPRoutes {
	
	/**
	 * This is where the camel routes for this unit test CIP are defined.
	 * PropertyPlaceholders can be used to insert values from CIAO config.
	 * @author Adam Hatherly
	 */
	@Override
	public void configure() {
		from("jetty:http://0.0.0.0:8765/test?traceEnabled=true").routeId("testRouteHTTP")
			.log("***** Config value = {{ConfigValue}} *****")
			.to("bean:loggingBean?method=logValue({{ConfigValue}})");
		super.configure();
	}
}
