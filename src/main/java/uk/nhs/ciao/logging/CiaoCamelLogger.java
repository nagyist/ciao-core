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
	
	public CiaoLogger getCiaoLogger() {
		return logger;
	}
	
	public Logger getLogger() {
		return logger.getLogger();
	}
	
	public Processor log(final LoggingLevel level, final CamelLogMessage message) {
		return log(level, ExceptionInclusion.INCLUDE, message);
	}
	
	public Processor log(final LoggingLevel level, final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		final boolean includeException = ExceptionInclusion.INCLUDE == exceptionInclusion;
		return new CiaoCamelLogProcessor(logger, level, message, includeException);
	}
	
	public Processor debug(final CamelLogMessage message) {
		return log(LoggingLevel.DEBUG, message);
	}
	
	public Processor debug(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.DEBUG, exceptionInclusion, message);
	}
	
	public Processor error(final CamelLogMessage message) {
		return log(LoggingLevel.ERROR, message);
	}
	
	public Processor error(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.ERROR, exceptionInclusion, message);
	}
	
	public Processor info(final CamelLogMessage message) {
		return log(LoggingLevel.INFO, message);
	}
	
	public Processor info(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.INFO, exceptionInclusion, message);
	}
	
	public Processor trace(final CamelLogMessage message) {
		return log(LoggingLevel.TRACE, message);
	}
	
	public Processor trace(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.TRACE, exceptionInclusion, message);
	}
	
	public Processor warn(final CamelLogMessage message) {
		return log(LoggingLevel.WARN, message);
	}
	
	public Processor warn(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.WARN, exceptionInclusion, message);
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
	
	public enum ExceptionInclusion {
		INCLUDE, OMIT;
	}
}
