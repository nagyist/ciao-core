package uk.nhs.ciao.logging;

/**
 * Represents a log message with modifiable key-value parameters
 */
public interface LogMessage {
	/**
	 * Sets the specified key-value parameter on the message.
	 * <p>
	 * The formatting used is determined by the concrete LogMessage implementation
	 * 
	 * @param key The parameter key
	 * @param value The parameter value (possibly null)
	 * @return <code>this</code> for method chaining
	 */
	LogMessage set(final String key, final Object value);

	/**
	 * Returns the formatted text of the log message
	 */
	@Override
	String toString();
}
