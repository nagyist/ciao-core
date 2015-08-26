package uk.nhs.itk.ciao.camel;

import org.apache.camel.Service;
import org.junit.Test;
import org.mockito.Mockito;

import uk.nhs.ciao.camel.CamelUtils;

/**
 * Unit tests for {@link CamelUtils}
 */
public class CamelUtilsTest {
	@Test
	public void testStopServiceQuietly() throws Exception {
		final Service service = Mockito.mock(Service.class);
		CamelUtils.stopQuietly(service);
		Mockito.verify(service).stop();
	}
	
	@Test
	public void testStopNullServiceQuietly() {
		final Service service = null;
		CamelUtils.stopQuietly(service);
	}
	
	@Test
	public void testStopServiceQuietlyTrapsException() throws Exception {
		final Service service = Mockito.mock(Service.class);
		Mockito.doThrow(Exception.class).when(service).stop();
		CamelUtils.stopQuietly(service);
		Mockito.verify(service).stop();
	}
	
	@Test
	public void testStopServicesQuietly() throws Exception {
		final Service service1 = Mockito.mock(Service.class);
		final Service service2 = Mockito.mock(Service.class);
		CamelUtils.stopQuietly(service1, service2);
		Mockito.verify(service1).stop();
		Mockito.verify(service2).stop();
	}
	
	@Test
	public void testStopNullServicesQuietly() {
		final Service[] services = null;
		CamelUtils.stopQuietly(services);
	}
	
	@Test
	public void testStopServicesQuietlyTrapsException() throws Exception {
		final Service service1 = Mockito.mock(Service.class);
		Mockito.doThrow(Exception.class).when(service1).stop();

		final Service service2 = Mockito.mock(Service.class);
		Mockito.doThrow(Exception.class).when(service2).stop();
		
		CamelUtils.stopQuietly(service1, service2);
		Mockito.verify(service1).stop();
		Mockito.verify(service2).stop();
	}
}
