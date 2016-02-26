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
	
	/**
	 * @return logger
	 */
	public CiaoLogger getCiaoLogger() {
		return logger;
	}
	
	/**
	 * @return logger
	 */
	public Logger getLogger() {
		return logger.getLogger();
	}
	
	/**
	 * Create Camel processor to do simple logging
	 * @param level Log level
	 * @param message Message to log
	 * @return Processor
	 */
	public Processor log(final LoggingLevel level, final CamelLogMessage message) {
		return log(level, ExceptionInclusion.INCLUDE, message);
	}
	
	/**
	 * Create Camel processor to do CIAO logging
	 * @param level Log level
	 * @param exceptionInclusion Whether to include exception in log entry
	 * @param message Message to log
	 * @return Processor
	 */
	public Processor log(final LoggingLevel level, final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		final boolean includeException = ExceptionInclusion.INCLUDE == exceptionInclusion;
		return new CiaoCamelLogProcessor(logger, level, message, includeException);
	}
	
	/**
	 * @param message
	 * @return Processor
	 */
	public Processor debug(final CamelLogMessage message) {
		return log(LoggingLevel.DEBUG, message);
	}
	
	/**
	 * @param exceptionInclusion
	 * @param message
	 * @return Processor
	 */
	public Processor debug(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.DEBUG, exceptionInclusion, message);
	}
	
	/**
	 * @param message
	 * @return Processor
	 */
	public Processor error(final CamelLogMessage message) {
		return log(LoggingLevel.ERROR, message);
	}
	
	/**
	 * @param exceptionInclusion
	 * @param message
	 * @return Processor
	 */
	public Processor error(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.ERROR, exceptionInclusion, message);
	}
	
	/**
	 * @param message
	 * @return Processor
	 */
	public Processor info(final CamelLogMessage message) {
		return log(LoggingLevel.INFO, message);
	}
	
	/**
	 * @param exceptionInclusion
	 * @param message
	 * @return Processor
	 */
	public Processor info(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.INFO, exceptionInclusion, message);
	}
	
	/**
	 * @param message
	 * @return Processor
	 */
	public Processor trace(final CamelLogMessage message) {
		return log(LoggingLevel.TRACE, message);
	}
	
	/**
	 * @param exceptionInclusion
	 * @param message
	 * @return Processor
	 */
	public Processor trace(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.TRACE, exceptionInclusion, message);
	}
	
	/**
	 * @param message
	 * @return Processor
	 */
	public Processor warn(final CamelLogMessage message) {
		return log(LoggingLevel.WARN, message);
	}
	
	/**
	 * @param exceptionInclusion
	 * @param message
	 * @return Processor
	 */
	public Processor warn(final ExceptionInclusion exceptionInclusion, final CamelLogMessage message) {
		return log(LoggingLevel.WARN, exceptionInclusion, message);
	}
	
	// static factory methods
	
	/**
	 * Static factory method
	 * @param name Logger name
	 * @return CiaoCamelLogger
	 */
	public static CiaoCamelLogger getLogger(final String name) {
		return getLogger(CiaoLogger.getLogger(name));
	}
	
	/**
	 * Static factory method
	 * @param clazz Logger class name
	 * @return CiaoCamelLogger
	 */
	public static CiaoCamelLogger getLogger(final Class<?> clazz) {
		return getLogger(CiaoLogger.getLogger(clazz));
	}
	
	/**
	 * Static factory method
	 * @param logger Logger
	 * @return CiaoCamelLogger
	 */
	public static CiaoCamelLogger getLogger(final Logger logger) {
		return getLogger(CiaoLogger.getLogger(logger));
	}
	
	/**
	 * Static factory method
	 * @param logger CIAO logger
	 * @return CiaoCamelLogger
	 */
	public static CiaoCamelLogger getLogger(final CiaoLogger logger) {
		return new CiaoCamelLogger(logger);
	}
	
	/**
	 * Enum to specify whether or not to include exceptions
	 */
	public enum ExceptionInclusion {
		/**
		 * Include Exceptions
		 */
		INCLUDE,
		/**
		 * Omit Exceptions
		 */
		OMIT;
	}
}
