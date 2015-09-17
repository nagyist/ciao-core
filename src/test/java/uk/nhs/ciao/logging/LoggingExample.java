package uk.nhs.ciao.logging;

import static uk.nhs.ciao.logging.LogMessages.logMsg;

public class LoggingExample {
	private static final CiaoLogger LOGGER = CiaoLogger.getLogger(LoggingExample.class);
	
	public static void main(final String[] args) throws Exception {
		LOGGER.info(logMsg("A custom message")
				.documentId("123")
				.eventName("EVENT-1")
				.set("CustomKey", "my-value"));
		
		LOGGER.info(logMsg("A different message")
				.interactionId("interaction")
				.set("CustomKey2", "my-other-value"));
	}
}
