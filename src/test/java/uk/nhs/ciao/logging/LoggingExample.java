package uk.nhs.ciao.logging;

import static uk.nhs.ciao.logging.CiaoLogMessage.logMsg;
import static uk.nhs.ciao.logging.CiaoCamelLogMessage.camelLogMsg;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultProducerTemplate;

import uk.nhs.ciao.camel.CamelUtils;

public class LoggingExample {
	private static final CiaoLogger LOGGER = CiaoLogger.getLogger(LoggingExample.class);
	private static final CiaoCamelLogger CAMEL_LOGGER = CiaoCamelLogger.getLogger(LoggingExample.class);
	
	public static void main(final String[] args) throws Exception {
		standardLoggerExample();
		
		final CamelContext context = new DefaultCamelContext();
		final ProducerTemplate producerTemplate = new DefaultProducerTemplate(context);
		try {
			context.start();
			producerTemplate.start();
			camelLoggerExample(context, producerTemplate);
		} finally {
			CamelUtils.stopQuietly(context, producerTemplate);
		}
	}
	
	private static void standardLoggerExample() {
		LOGGER.info(logMsg("A custom message")
				.documentId("123")
				.eventName("EVENT-1")
				.set("CustomKey", "my-value"));
		
		LOGGER.info(logMsg("A different message")
				.interactionId("interaction")
				.set("CustomKey2", "my-other-value"));
	}
	
	private static void camelLoggerExample(final CamelContext context, final ProducerTemplate producerTemplate) throws Exception {
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:test").process(
					CAMEL_LOGGER.info(
						camelLogMsg("A message being produced by camel")
						.documentId("${header.documentId}")
						.eventName(body())
					));
			}
		});
		
		final Exchange exchange = new DefaultExchange(context);
		exchange.getIn().setHeader("documentId", "my-document-id");
		exchange.getIn().setBody("EVENT-2");
		
		producerTemplate.send("direct:test", exchange);
	}
}
