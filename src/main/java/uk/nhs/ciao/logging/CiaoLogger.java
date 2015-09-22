package uk.nhs.ciao.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger to write {@link LogMessage}s at the specified logging level.
 * <p>
 * Instances of CiaoLogger delegate calls to the underlying SLF4J {@link Logger},
 * while allowing the {@link LogMessage} implementation to provide standardised
 * formatting of the output text.
 * <p>
 * A {@link LogMessage} can be obtained via {@link CiaoLogMessage#logMsg(CharSequence)}.
 */
public class CiaoLogger {
	private final Logger logger;
	
	private CiaoLogger(final Logger logger) {
		this.logger = logger;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public void debug(final LogMessage message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message.toString());
		}
	}
	
	public void debug(final LogMessage message, final Throwable throwable) {
		if (logger.isDebugEnabled()) {
			logger.debug(message.toString(), throwable);
		}
	}
	
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}
	
	public void error(final LogMessage message) {
		if (logger.isErrorEnabled()) {
			logger.error(message.toString());
		}
	}
	
	public void error(final LogMessage message, final Throwable throwable) {
		if (logger.isErrorEnabled()) {
			logger.error(message.toString(), throwable);
		}
	}
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	public void info(final LogMessage message) {
		if (logger.isInfoEnabled()) {
			logger.info(message.toString());
		}
	}
	
	public void info(final LogMessage message, final Throwable throwable) {
		if (logger.isInfoEnabled()) {
			logger.info(message.toString(), throwable);
		}
	}
	
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}
	
	public void trace(final LogMessage message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message.toString());
		}
	}
	
	
	public void trace(final LogMessage message, final Throwable throwable) {
		if (logger.isTraceEnabled()) {
			logger.trace(message.toString(), throwable);
		}
	}
	
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}
	
	public void warn(final LogMessage message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message.toString());
		}
	}
	
	public void warn(final LogMessage message, final Throwable throwable) {
		if (logger.isWarnEnabled()) {
			logger.warn(message.toString(), throwable);
		}
	}
	
	@Override
	public String toString() {
		return logger.toString();
	}
	
	public static CiaoLogger getLogger(final String name) {
		return new CiaoLogger(LoggerFactory.getLogger(name));
	}
	
	public static CiaoLogger getLogger(final Class<?> clazz) {
		return new CiaoLogger(LoggerFactory.getLogger(clazz));
	}
	
	public static CiaoLogger getLogger(final Logger logger) {
		return new CiaoLogger(logger);
	}
}
