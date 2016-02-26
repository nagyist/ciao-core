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
	
	/**
	 * @return Logger
	 */
	public Logger getLogger() {
		return logger;
	}
	
	/**
	 * @return true if debug enabled
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	/**
	 * @param message
	 */
	public void debug(final LogMessage message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message.toString());
		}
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void debug(final LogMessage message, final Throwable throwable) {
		if (logger.isDebugEnabled()) {
			logger.debug(message.toString(), throwable);
		}
	}
	
	/**
	 * @return true if error enabled
	 */
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}
	
	/**
	 * @param message
	 */
	public void error(final LogMessage message) {
		if (logger.isErrorEnabled()) {
			logger.error(message.toString());
		}
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void error(final LogMessage message, final Throwable throwable) {
		if (logger.isErrorEnabled()) {
			logger.error(message.toString(), throwable);
		}
	}
	
	/**
	 * @return true if info enabled
	 */
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	/**
	 * @param message
	 */
	public void info(final LogMessage message) {
		if (logger.isInfoEnabled()) {
			logger.info(message.toString());
		}
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void info(final LogMessage message, final Throwable throwable) {
		if (logger.isInfoEnabled()) {
			logger.info(message.toString(), throwable);
		}
	}
	
	/**
	 * @return true if trace enabled
	 */
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}
	
	/**
	 * @param message
	 */
	public void trace(final LogMessage message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message.toString());
		}
	}
	
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void trace(final LogMessage message, final Throwable throwable) {
		if (logger.isTraceEnabled()) {
			logger.trace(message.toString(), throwable);
		}
	}
	
	/**
	 * @return true if warning enabled
	 */
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}
	
	/**
	 * @param message
	 */
	public void warn(final LogMessage message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message.toString());
		}
	}
	
	/**
	 * @param message
	 * @param throwable
	 */
	public void warn(final LogMessage message, final Throwable throwable) {
		if (logger.isWarnEnabled()) {
			logger.warn(message.toString(), throwable);
		}
	}
	
	@Override
	public String toString() {
		return logger.toString();
	}
	
	/**
	 * @param name
	 * @return CiaoLogger
	 */
	public static CiaoLogger getLogger(final String name) {
		return new CiaoLogger(LoggerFactory.getLogger(name));
	}
	
	/**
	 * @param clazz
	 * @return CiaoLogger
	 */
	public static CiaoLogger getLogger(final Class<?> clazz) {
		return new CiaoLogger(LoggerFactory.getLogger(clazz));
	}
	
	/**
	 * @param logger
	 * @return CiaoLogger
	 */
	public static CiaoLogger getLogger(final Logger logger) {
		return new CiaoLogger(logger);
	}
}
