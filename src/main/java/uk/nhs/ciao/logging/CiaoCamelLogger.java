package uk.nhs.ciao.logging;

import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.slf4j.Logger;

/**
 * Creates Camel {@link Processor}s to write messages generated at runtime
 * from Camel {@link Expression}s to the specified backing {@link CiaoLogger}.
 * <p>
 * See {@link CiaoCamelLogMessage#camelLogMsg(CharSequence)} to create messages
 * while building the route.
 */
public class CiaoCamelLogger {
	private final CiaoLogger logger;
	
	private CiaoCamelLogger(final CiaoLogger logger) {
		this.logger = logger;
	}
	
	public Processor log(final LoggingLevel level, final CamelLogMessage message) {
		return new CiaoCamelLogProcessor(logger, level, message);
	}
	
	public Processor debug(final CamelLogMessage message) {
		return log(LoggingLevel.DEBUG, message);
	}
	
	public Processor error(final CamelLogMessage message) {
		return log(LoggingLevel.ERROR, message);
	}
	
	public Processor info(final CamelLogMessage message) {
		return log(LoggingLevel.INFO, message);
	}
	
	public Processor trace(final CamelLogMessage message) {
		return log(LoggingLevel.TRACE, message);
	}
	
	public Processor warn(final CamelLogMessage message) {
		return log(LoggingLevel.WARN, message);
	}
	
	// static factory methods
	
	public static CiaoCamelLogger getLogger(final String name) {
		return getLogger(CiaoLogger.getLogger(name));
	}
	
	public static CiaoCamelLogger getLogger(final Class<?> clazz) {
		return getLogger(CiaoLogger.getLogger(clazz));
	}
	
	public static CiaoCamelLogger getLogger(final Logger logger) {
		return getLogger(CiaoLogger.getLogger(logger));
	}
	
	public static CiaoCamelLogger getLogger(final CiaoLogger logger) {
		return new CiaoCamelLogger(logger);
	}
}
