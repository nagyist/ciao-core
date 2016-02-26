package uk.nhs.ciao.logging;

import static uk.nhs.ciao.logging.CiaoLogParameters.*;

import org.apache.camel.Expression;

/**
 * A CamelLogMessage with additional methods to handle the standard CIAO properties.
 * 
 * @see #camelLogMsg(CharSequence)
 */
public class CiaoCamelLogMessage extends CamelLogMessage {
	/**
	 * Constructs a camel log message from the specified text
	 *
	 * @param text The static text of the message
	 * @return A camel log message configured with the specified text
	 */
	public static CiaoCamelLogMessage camelLogMsg(final CharSequence text) {
		return new CiaoCamelLogMessage(text);
	}
	
	private CiaoCamelLogMessage(final CharSequence text) {
		super(text);
	}
	
	@Override
	public CiaoCamelLogMessage set(final String name, final Expression expression) {
		super.set(name, expression);
		return this;
	}
	
	@Override
	public CiaoCamelLogMessage set(final String name, final String expression) {
		super.set(name, expression);
		return this;
	}

	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage eventName(final String expression) {
		return set(EVENT_NAME, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage eventName(final Expression expression) {
		return set(EVENT_NAME, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage state(final String expression) {
		return set(STATE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage state(final Expression expression) {
		return set(STATE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage fromState(final String expression) {
		return set(FROM_STATE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage fromState(final Expression expression) {
		return set(FROM_STATE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage toState(final String expression) {
		return set(TO_STATE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage toState(final Expression expression) {
		return set(TO_STATE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage inputDirectory(final String expression) {
		return set(INPUT_DIRECTORY, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage inputDirectory(final Expression expression) {
		return set(INPUT_DIRECTORY, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage originalFileName(final String expression) {
		return set(ORIGINAL_FILE_NAME, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage originalFileName(final Expression expression) {
		return set(ORIGINAL_FILE_NAME, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage documentId(final String expression) {
		return set(DOCUMENT_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage documentId(final Expression expression) {
		return set(DOCUMENT_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage senderAsid(final String expression) {
		return set(SENDER_ASID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage 
	 */
	public CiaoCamelLogMessage senderAsid(final Expression expression) {
		return set(SENDER_ASID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage receiverAsid(final String expression) {
		return set(RECEIVER_ASID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage receiverAsid(final Expression expression) {
		return set(RECEIVER_ASID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage receiverODSCode(final String expression) {
		return set(RECEIVER_ODS_CODE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage receiverODSCode(final Expression expression) {
		return set(RECEIVER_ODS_CODE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage receiverMHSPartyKey(final String expression) {
		return set(RECEIVER_MHS_PARTY_KEY, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage receiverMHSPartyKey(final Expression expression) {
		return set(RECEIVER_MHS_PARTY_KEY, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage interactionId(final String expression) {
		return set(INTERACTION_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage interactionId(final Expression expression) {
		return set(INTERACTION_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage documentProperties(final String expression) {
		return set(DOCUMENT_PROPERTIES, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage documentProperties(final Expression expression) {
		return set(DOCUMENT_PROPERTIES, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage fileName(final String expression) {
		return set(FILE_NAME, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage fileName(final Expression expression) {
		return set(FILE_NAME, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage uri(final String expression) {
		return set(URI, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage uri(final Expression expression) {
		return set(URI, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage action(final String expression) {
		return set(ACTION, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage action(final Expression expression) {
		return set(ACTION, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage service(final String expression) {
		return set(SERVICE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage service(final Expression expression) {
		return set(SERVICE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage asid(final String expression) {
		return set(ASID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage asid(final Expression expression) {
		return set(ASID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage odsCode(final String expression) {
		return set(ODS_CODE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage odsCode(final Expression expression) {
		return set(ODS_CODE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage key(final String expression) {
		return set(KEY, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage key(final Expression expression) {
		return set(KEY, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage address(final String expression) {
		return set(ADDRESS, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */ 
	public CiaoCamelLogMessage address(final Expression expression) {
		return set(ADDRESS, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage soapAction(final String expression) {
		return set(SOAP_ACTION, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage soapAction(final Expression expression) {
		return set(SOAP_ACTION, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage itkTrackingId(final String expression) {
		return set(ITK_TRACKING_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage itkTrackingId(final Expression expression) {
		return set(ITK_TRACKING_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */ 
	public CiaoCamelLogMessage distributionEnvelopeService(final String expression) {
		return set(DISTRIBUTION_ENVELOPE_SERVICE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage distributionEnvelopeService(final Expression expression) {
		return set(DISTRIBUTION_ENVELOPE_SERVICE, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage ebxmlMessageId(final String expression) {
		return set(EBXML_MESSAGE_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage ebxmlMessageId(final Expression expression) {
		return set(EBXML_MESSAGE_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */ 
	public CiaoCamelLogMessage ebxmlRefToMessageId(final String expression) {
		return set(EBXML_REF_TO_MESSAGE_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage ebxmlRefToMessageId(final Expression expression) {
		return set(EBXML_REF_TO_MESSAGE_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage workflowId(final String expression) {
		return set(WORKFLOW_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage workflowId(final Expression expression) {
		return set(WORKFLOW_ID, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage fromDTS(final String expression) {
		return set(FROM_DTS, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage fromDTS(final Expression expression) {
		return set(FROM_DTS, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage toDTS(final String expression) {
		return set(TO_DTS, expression);
	}
	
	/**
	 * @param expression
	 * @return CiaoCamelLogMessage
	 */
	public CiaoCamelLogMessage toDTS(final Expression expression) {
		return set(TO_DTS, expression);
	}
}
