package uk.nhs.ciao.logging;

import static uk.nhs.ciao.logging.CiaoLogParameters.*;

public class CiaoLogMessage extends ReusableLogMessage {
	
	private static final ThreadLocal<CiaoLogMessage> INSTANCE = new ThreadLocal<CiaoLogMessage>() {
		protected CiaoLogMessage initialValue() {
			return new CiaoLogMessage();
		};
	};
	
	public static CiaoLogMessage logMsg(final CharSequence text) {
		final CiaoLogMessage message = INSTANCE.get();
		message.reset(text);
		return message;
	}
	
	@Override
	public CiaoLogMessage set(final String key, final Object value) {
		super.set(key, value);
		return this;
	}
	
	public CiaoLogMessage eventName(final Object value) {
		return set(EVENT_NAME, value);
	}
	
	public CiaoLogMessage fromState(final Object value) {
		return set(FROM_STATE, value);
	}
	
	public CiaoLogMessage toState(final Object value) {
		return set(TO_STATE, value);
	}
	
	public CiaoLogMessage inputDirectory(final Object value) {
		return set(INPUT_DIRECTORY, value);
	}
	
	public CiaoLogMessage originalFileName(final Object value) {
		return set(ORIGINAL_FILE_NAME, value);
	}
	
	public CiaoLogMessage documentId(final Object value) {
		return set(DOCUMENT_ID, value);
	}
	
	public CiaoLogMessage senderAsid(final Object value) {
		return set(SENDER_ASID, value);
	}
	
	public CiaoLogMessage receiverAsid(final Object value) {
		return set(RECEIVER_ASID, value);
	}
	
	public CiaoLogMessage receiverODSCode(final Object value) {
		return set(RECEIVER_ODS_CODE, value);
	}
	
	public CiaoLogMessage interactionId(final Object value) {
		return set(INTERACTION_ID, value);
	}
	
	public CiaoLogMessage documentProperties(final Object value) {
		return set(DOCUMENT_PROPERTIES, value);
	}
	
	public CiaoLogMessage fileName(final Object value) {
		return set(FILE_NAME, value);
	}
	
	public CiaoLogMessage uri(final Object value) {
		return set(URI, value);
	}
	
	public CiaoLogMessage action(final Object value) {
		return set(ACTION, value);
	}
	
	public CiaoLogMessage service(final Object value) {
		return set(SERVICE, value);
	}
	
	public CiaoLogMessage asid(final Object value) {
		return set(ASID, value);
	}
	
	public CiaoLogMessage odsCode(final Object value) {
		return set(ODS_CODE, value);
	}
	
	public CiaoLogMessage key(final Object value) {
		return set(KEY, value);
	}
	
	public CiaoLogMessage address(final Object value) {
		return set(ADDRESS, value);
	}
	
	public CiaoLogMessage soapAction(final Object value) {
		return set(SOAP_ACTION, value);
	}
	
	public CiaoLogMessage itkTrackingId(final Object value) {
		return set(ITK_TRACKING_ID, value);
	}
	
	public CiaoLogMessage distributionEnvelopeService(final Object value) {
		return set(DISTRIBUTION_ENVELOPE_SERVICE, value);
	}
}
