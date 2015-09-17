package uk.nhs.ciao.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CiaoLogger {
	private final Logger logger;
	
	private CiaoLogger(final Logger logger) {
		this.logger = logger;
	}
	
	public boolean isEnabled(final LogLevel level) {
		return level.isEnabled(logger);
	}
	
	public void log(final LogLevel level, final LogMessage message) {
		if (level.isEnabled(logger)) {
			level.log(logger, message.toString());
		}
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public void debug(final LogMessage message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message.toString());
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
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	public void info(final LogMessage message) {
		if (logger.isInfoEnabled()) {
			logger.info(message.toString());
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
	
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}
	
	public void warn(final LogMessage message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message.toString());
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
