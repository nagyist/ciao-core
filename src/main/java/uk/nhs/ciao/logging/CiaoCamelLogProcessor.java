package uk.nhs.ciao.logging;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;

/**
 * A camel processor which interprets a {@link CamelLogMessage} at runtime (via
 * Camel expressions executed on the incoming exchange), and generates a
 * log message via the specified {@link CiaoLogger}
 * <p>
 * Instances of this class are thread-safe as long as the underlying {@link CamelLogMessage}
 * is also thread-safe (or is not mutated after construction)
 */
class CiaoCamelLogProcessor implements Processor {
	private final CiaoLogger logger;
	private final LoggingLevel level;
	private final CamelLogMessage message;
	
	public CiaoCamelLogProcessor(final CiaoLogger logger, final LoggingLevel level, final CamelLogMessage message) {
		this.logger = logger;
		this.level = level;
		this.message = message;
	}
	
	@Override
	public void process(final Exchange exchange) throws Exception {
		switch(level) {
		case DEBUG:
			debug(exchange);
			break;
		case ERROR:
			error(exchange);
			break;
		case INFO:
			info(exchange);
			break;
		case TRACE:
			trace(exchange);
			break;
		case WARN:
			warn(exchange);
			break;
		case OFF:
			// NOOP
			break;
		}
	}
	
	private void debug(final Exchange exchange) {
		if (logger.isDebugEnabled()) {
			final Exception exception = exchange.getException();
			if (exception == null) {
				logger.debug(message.toLogMessage(exchange));
			} else {
				logger.debug(message.toLogMessage(exchange), exception);
			}
		}
	}
	
	private void error(final Exchange exchange) {
		if (logger.isErrorEnabled()) {
			final Exception exception = exchange.getException();
			if (exception == null) {
				logger.error(message.toLogMessage(exchange));
			} else {
				logger.error(message.toLogMessage(exchange), exception);
			}
		}		
	}
	
	private void info(final Exchange exchange) {
		if (logger.isInfoEnabled()) {
			final Exception exception = exchange.getException();
			if (exception == null) {
				logger.info(message.toLogMessage(exchange));
			} else {
				logger.info(message.toLogMessage(exchange), exception);
			}
		}
	}
	
	private void trace(final Exchange exchange) {
		if (logger.isTraceEnabled()) {
			final Exception exception = exchange.getException();
			if (exception == null) {
				logger.trace(message.toLogMessage(exchange));
			} else {
				logger.trace(message.toLogMessage(exchange), exception);
			}
		}
	}
	
	private void warn(final Exchange exchange) {
		if (logger.isWarnEnabled()) {
			final Exception exception = exchange.getException();
			if (exception == null) {
				logger.warn(message.toLogMessage(exchange));
			} else {
				logger.warn(message.toLogMessage(exchange), exception);
			}
		}
	}
}