package uk.nhs.ciao.logging;

public class LogMessages {
	private static ThreadLocal<StandardLogMessage> LOG_MESSAGE = new ThreadLocal<StandardLogMessage>() {
		protected StandardLogMessage initialValue() {
			return new StandardLogMessage();
		};
	};
	
	public static StandardLogMessage logMsg(final CharSequence text) {
		final StandardLogMessage message = LOG_MESSAGE.get();
		message.reset(text);
		return message;
	}
}
