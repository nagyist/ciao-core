package uk.nhs.ciao.camel;

import org.apache.camel.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods related to Camel
 */
public final class CamelUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(CamelUtils.class);
	
	/**
	 * Stops the specified Camel service logging the exception (if thrown)
	 * 
	 * @param service The service to stop
	 */
	public static void stopQuietly(final Service service) {
		if (service == null) {
			return;
		}
		
		try {
			service.stop();
		} catch (Exception e) {
			LOGGER.warn("Exception while stopping Camel service: {}", service, e);
		}
	}
	
	/**
	 * Stops the specified Camel services logging any exceptions (if thrown)
	 * <p>
	 * Services are stopped in the order specified
	 * 
	 * @param services The services to stop
	 */
	public static void stopQuietly(final Service... services) {
		if (services == null) {
			return;
		}
		
		for (final Service service: services) {
			stopQuietly(service);
		}
	}
}
