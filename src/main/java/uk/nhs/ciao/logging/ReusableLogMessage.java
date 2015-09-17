package uk.nhs.ciao.logging;

class ReusableLogMessage implements LogMessage {
	private final StringBuilder builder = new StringBuilder();
	
	void clear() {
		this.builder.setLength(0);
	}
	
	void reset(final CharSequence text) {
		clear();
		if (text != null) {
			this.builder.append(text);
		}
	}
	
	@Override
	public ReusableLogMessage set(final String key, final Object value) {
		if (builder.length() > 0) {
			builder.append(' ');
		}
		
		builder.append(key).append('=').append(value);
		return this;
	}
	
	@Override
	public String toString() {
		return builder.toString();
	}
}
