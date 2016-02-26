package uk.nhs.ciao.logging;

/**
 * Reusable log message backed by a single StringBuilder.
 * <p>
 * The message is not thread-safe. If concurrent access is required, either
 * external synchronisation or thread-confinement is required (e.g. a ThreadLocal).
 * <p>
 * The log message can be reused via {@link #reset(CharSequence)}.
 * <p>
 * Package scoped so that implementation details to not leak into the main API.
 */
@SuppressWarnings("javadoc")
class ReusableLogMessage implements LogMessage {
	private final StringBuilder builder = new StringBuilder();
	private boolean beforeProperties;
	
	void clear() {
		this.builder.setLength(0);
		this.beforeProperties = true;
	}
	
	void reset(final CharSequence text) {
		clear();
		if (text != null && text.length() > 0) {
			this.builder.append(text);
			
			// For consistent formatting - ensure that the text ends with a full-stop
			if (text.charAt(text.length() - 1) != '.') {
				this.builder.append('.');
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReusableLogMessage set(final String key, final Object value) {
		if (beforeProperties) {
			if (builder.length() > 0) {
				builder.append(' ');
			}
			
			builder.append("Values -->");
		}
		
		beforeProperties = false;
		
		// parameters are separated by a space
		builder.append(' ').append(key).append('=').append(value);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * The format of the message is:
	 * "<code>{text}. Values --> {key1}={value1} {key2}={value2}...</code>"
	 */
	@Override
	public String toString() {
		return builder.toString();
	}
}
