package uk.nhs.ciao.logging;

import org.apache.camel.LoggingLevel;
import org.slf4j.Logger;

public enum LogLevel {
	DEBUG(LoggingLevel.DEBUG) {
		@Override
		public boolean isEnabled(final Logger logger) {
			return logger.isDebugEnabled();
		}
		
		@Override
		public void log(final Logger logger, final String message) {
			logger.debug(message);
		}
		
		@Override
		public void log(final Logger logger, final String message, final Throwable throwable) {
			logger.debug(message, throwable);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object arg) {
			logger.debug(format, arg);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object... args) {
			logger.debug(format, args);
		}
	},
	
	ERROR(LoggingLevel.ERROR) {
		@Override
		public boolean isEnabled(final Logger logger) {
			return logger.isErrorEnabled();
		}
		
		@Override
		public void log(final Logger logger, final String message) {
			logger.error(message);
		}
		
		@Override
		public void log(final Logger logger, final String message, final Throwable throwable) {
			logger.error(message, throwable);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object arg) {
			logger.error(format, arg);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object... args) {
			logger.error(format, args);
		}
	},
	
	INFO(LoggingLevel.INFO) {
		@Override
		public boolean isEnabled(final Logger logger) {
			return logger.isInfoEnabled();
		}
		
		@Override
		public void log(final Logger logger, final String message) {
			logger.info(message);
		}
		
		
		@Override
		public void log(final Logger logger, final String message, final Throwable throwable) {
			logger.info(message, throwable);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object arg) {
			logger.info(format, arg);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object... args) {
			logger.info(format, args);
		}
	},
	
	OFF(LoggingLevel.OFF) {
		@Override
		public boolean isEnabled(final Logger logger) {
			return false;
		}
		
		@Override
		public void log(final Logger logger, final String message) {
			// NOOP
		}
		
		@Override
		public void log(final Logger logger, final String message, final Throwable throwable) {
			// NOOP
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object arg) {
			// NOOP
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object... args) {
			// NOOP
		}
	},
	
	TRACE(LoggingLevel.TRACE) {
		@Override
		public boolean isEnabled(final Logger logger) {
			return logger.isTraceEnabled();
		}
		
		@Override
		public void log(final Logger logger, final String message) {
			logger.trace(message);
		}
		
		@Override
		public void log(final Logger logger, final String message, final Throwable throwable) {
			logger.trace(message, throwable);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object arg) {
			logger.trace(format, arg);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object... args) {
			logger.trace(format, args);
		}
	},
	
	WARN(LoggingLevel.WARN) {
		@Override
		public boolean isEnabled(final Logger logger) {
			return logger.isWarnEnabled();
		}
		
		@Override
		public void log(final Logger logger, final String message) {
			logger.warn(message);
		}
		
		@Override
		public void log(final Logger logger, final String message, final Throwable throwable) {
			logger.warn(message, throwable);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object arg) {
			logger.warn(format, arg);
		}
		
		@Override
		public void log(final Logger logger, final String format, final Object... args) {
			logger.warn(format, args);
		}
	};
	
	private final LoggingLevel loggingLevel;
	
	private LogLevel(final LoggingLevel loggingLevel) {
		this.loggingLevel = loggingLevel;
	}
	
	public LoggingLevel getLoggingLevel() {
		return loggingLevel;
	}
	
	public abstract boolean isEnabled(final Logger logger);
	public abstract void log(final Logger logger, final String message);
	public abstract void log(final Logger logger, final String message, final Throwable throwable);	
	public abstract void log(final Logger logger, final String format, final Object arg);
	public abstract void log(final Logger logger, final String format, final Object... args);
}
