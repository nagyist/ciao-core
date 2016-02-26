package uk.nhs.ciao.logging;

import static uk.nhs.ciao.logging.CiaoLogParameters.*;

/**
 * A CIAO-specific log message, with any appropriate key-value pairs included
 *
 */
public class CiaoLogMessage extends ReusableLogMessage {
	
	private static final ThreadLocal<CiaoLogMessage> INSTANCE = new ThreadLocal<CiaoLogMessage>() {
		protected CiaoLogMessage initialValue() {
			return new CiaoLogMessage();
		};
	};
	
	/**
	 * @param text
	 * @return CiaoLogMessage
	 */
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
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage eventName(final Object value) {
		return set(EVENT_NAME, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage state(final Object value) {
		return set(STATE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage fromState(final Object value) {
		return set(FROM_STATE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */ 
	public CiaoLogMessage toState(final Object value) {
		return set(TO_STATE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage inputDirectory(final Object value) {
		return set(INPUT_DIRECTORY, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage originalFileName(final Object value) {
		return set(ORIGINAL_FILE_NAME, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage documentId(final Object value) {
		return set(DOCUMENT_ID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage senderAsid(final Object value) {
		return set(SENDER_ASID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage receiverAsid(final Object value) {
		return set(RECEIVER_ASID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage receiverODSCode(final Object value) {
		return set(RECEIVER_ODS_CODE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage receiverMHSPartyKey(final Object value) {
		return set(RECEIVER_MHS_PARTY_KEY, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage interactionId(final Object value) {
		return set(INTERACTION_ID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage documentProperties(final Object value) {
		return set(DOCUMENT_PROPERTIES, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage fileName(final Object value) {
		return set(FILE_NAME, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage uri(final Object value) {
		return set(URI, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage action(final Object value) {
		return set(ACTION, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage service(final Object value) {
		return set(SERVICE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage asid(final Object value) {
		return set(ASID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage odsCode(final Object value) {
		return set(ODS_CODE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage key(final Object value) {
		return set(KEY, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage address(final Object value) {
		return set(ADDRESS, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage soapAction(final Object value) {
		return set(SOAP_ACTION, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage itkTrackingId(final Object value) {
		return set(ITK_TRACKING_ID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */
	public CiaoLogMessage distributionEnvelopeService(final Object value) {
		return set(DISTRIBUTION_ENVELOPE_SERVICE, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */ 
	public CiaoLogMessage ebxmlMessageId(final Object value) {
		return set(EBXML_MESSAGE_ID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */ 
	public CiaoLogMessage ebxmlRefToMessageId(final Object value) {
		return set(EBXML_REF_TO_MESSAGE_ID, value);
	}

	/**
	 * @param value
	 * @return CiaoLogMessage
	 */ 
	public CiaoLogMessage workflowId(final Object value) {
		return set(WORKFLOW_ID, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */ 
	public CiaoLogMessage fromDTS(final Object value) {
		return set(FROM_DTS, value);
	}
	
	/**
	 * @param value
	 * @return CiaoLogMessage
	 */ 
	public CiaoLogMessage toDTS(final Object value) {
		return set(TO_DTS, value);
	}
}
