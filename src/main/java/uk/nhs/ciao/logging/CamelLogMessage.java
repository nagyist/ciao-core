package uk.nhs.ciao.logging;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.ExpressionBuilder;

/**
 * A log message built from static text, and a series of named Camel expressions (evaluated at runtime)
 */
public class CamelLogMessage {
	private final CharSequence text;
	private final Map<String, Expression> parameters;
	
	/**
	 * @param text
	 */
	public CamelLogMessage(final CharSequence text) {
		this.text = text;
		this.parameters = new LinkedHashMap<String, Expression>();
	}
	
	/**
	 * Sets the named parameter using the specified expression
	 * @param name Parameter name
	 * @param expression Expression
	 * @return this
	 */
	public CamelLogMessage set(final String name, final Expression expression) {
		parameters.put(name, expression);
		return this;
	}
	
	/**
	 * Sets the named parameter using a simple expression
	 * @param name Parameter name
	 * @param expression Expression
	 * @return this
	 */
	public CamelLogMessage set(final String name, final String expression) {
		return set(name, ExpressionBuilder.simpleExpression(expression));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return "CamelLogMessage {text=" + text + ", parameters=" + parameters + "}";
	}

	/**
	 * Generates a log message by evaluating the configured expressions against the specified
	 * exchange at runtime.
	 * <p>
	 * Package scoped to prevent implementation details leaking into the main API
	 */
	LogMessage toLogMessage(final Exchange exchange) {
		final LogMessage message = CiaoLogMessage.logMsg(text);
		for (final Entry<String, Expression> entry: parameters.entrySet()) {
			final Object value = entry.getValue().evaluate(exchange, Object.class);
			message.set(entry.getKey(), value);
		}
		return message;
	}
}
