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

	public CiaoCamelLogMessage eventName(final String expression) {
		return set(EVENT_NAME, expression);
	}
	
	public CiaoCamelLogMessage eventName(final Expression expression) {
		return set(EVENT_NAME, expression);
	}
	
	public CiaoCamelLogMessage state(final String expression) {
		return set(STATE, expression);
	}
	
	public CiaoCamelLogMessage state(final Expression expression) {
		return set(STATE, expression);
	}
	
	public CiaoCamelLogMessage fromState(final String expression) {
		return set(FROM_STATE, expression);
	}
	
	public CiaoCamelLogMessage fromState(final Expression expression) {
		return set(FROM_STATE, expression);
	}
	
	public CiaoCamelLogMessage toState(final String expression) {
		return set(TO_STATE, expression);
	}
	
	public CiaoCamelLogMessage toState(final Expression expression) {
		return set(TO_STATE, expression);
	}
	
	public CiaoCamelLogMessage inputDirectory(final String expression) {
		return set(INPUT_DIRECTORY, expression);
	}
	
	public CiaoCamelLogMessage inputDirectory(final Expression expression) {
		return set(INPUT_DIRECTORY, expression);
	}
	
	public CiaoCamelLogMessage originalFileName(final String expression) {
		return set(ORIGINAL_FILE_NAME, expression);
	}
	
	public CiaoCamelLogMessage originalFileName(final Expression expression) {
		return set(ORIGINAL_FILE_NAME, expression);
	}
	
	public CiaoCamelLogMessage documentId(final String expression) {
		return set(DOCUMENT_ID, expression);
	}
	
	public CiaoCamelLogMessage documentId(final Expression expression) {
		return set(DOCUMENT_ID, expression);
	}
	
	public CiaoCamelLogMessage senderAsid(final String expression) {
		return set(SENDER_ASID, expression);
	}
	
	public CiaoCamelLogMessage senderAsid(final Expression expression) {
		return set(SENDER_ASID, expression);
	}
	
	public CiaoCamelLogMessage receiverAsid(final String expression) {
		return set(RECEIVER_ASID, expression);
	}
	
	public CiaoCamelLogMessage receiverAsid(final Expression expression) {
		return set(RECEIVER_ASID, expression);
	}
	
	public CiaoCamelLogMessage receiverODSCode(final String expression) {
		return set(RECEIVER_ODS_CODE, expression);
	}
	
	public CiaoCamelLogMessage receiverODSCode(final Expression expression) {
		return set(RECEIVER_ODS_CODE, expression);
	}
	
	public CiaoCamelLogMessage receiverMHSPartyKey(final String expression) {
		return set(RECEIVER_MHS_PARTY_KEY, expression);
	}
	
	public CiaoCamelLogMessage receiverMHSPartyKey(final Expression expression) {
		return set(RECEIVER_MHS_PARTY_KEY, expression);
	}
	
	public CiaoCamelLogMessage interactionId(final String expression) {
		return set(INTERACTION_ID, expression);
	}
	
	public CiaoCamelLogMessage interactionId(final Expression expression) {
		return set(INTERACTION_ID, expression);
	}
	
	public CiaoCamelLogMessage documentProperties(final String expression) {
		return set(DOCUMENT_PROPERTIES, expression);
	}
	
	public CiaoCamelLogMessage documentProperties(final Expression expression) {
		return set(DOCUMENT_PROPERTIES, expression);
	}
	
	public CiaoCamelLogMessage fileName(final String expression) {
		return set(FILE_NAME, expression);
	}
	
	public CiaoCamelLogMessage fileName(final Expression expression) {
		return set(FILE_NAME, expression);
	}
	
	public CiaoCamelLogMessage uri(final String expression) {
		return set(URI, expression);
	}
	
	public CiaoCamelLogMessage uri(final Expression expression) {
		return set(URI, expression);
	}
	
	public CiaoCamelLogMessage action(final String expression) {
		return set(ACTION, expression);
	}
	
	public CiaoCamelLogMessage action(final Expression expression) {
		return set(ACTION, expression);
	}
	
	public CiaoCamelLogMessage service(final String expression) {
		return set(SERVICE, expression);
	}
	
	public CiaoCamelLogMessage service(final Expression expression) {
		return set(SERVICE, expression);
	}
	
	public CiaoCamelLogMessage asid(final String expression) {
		return set(ASID, expression);
	}
	
	public CiaoCamelLogMessage asid(final Expression expression) {
		return set(ASID, expression);
	}
	
	public CiaoCamelLogMessage odsCode(final String expression) {
		return set(ODS_CODE, expression);
	}
	
	public CiaoCamelLogMessage odsCode(final Expression expression) {
		return set(ODS_CODE, expression);
	}
	
	public CiaoCamelLogMessage key(final String expression) {
		return set(KEY, expression);
	}
	
	public CiaoCamelLogMessage key(final Expression expression) {
		return set(KEY, expression);
	}
	
	public CiaoCamelLogMessage address(final String expression) {
		return set(ADDRESS, expression);
	}
	
	public CiaoCamelLogMessage address(final Expression expression) {
		return set(ADDRESS, expression);
	}
	
	public CiaoCamelLogMessage soapAction(final String expression) {
		return set(SOAP_ACTION, expression);
	}
	
	public CiaoCamelLogMessage soapAction(final Expression expression) {
		return set(SOAP_ACTION, expression);
	}
	
	public CiaoCamelLogMessage itkTrackingId(final String expression) {
		return set(ITK_TRACKING_ID, expression);
	}
	
	public CiaoCamelLogMessage itkTrackingId(final Expression expression) {
		return set(ITK_TRACKING_ID, expression);
	}
	
	public CiaoCamelLogMessage distributionEnvelopeService(final String expression) {
		return set(DISTRIBUTION_ENVELOPE_SERVICE, expression);
	}
	
	public CiaoCamelLogMessage distributionEnvelopeService(final Expression expression) {
		return set(DISTRIBUTION_ENVELOPE_SERVICE, expression);
	}
	
	public CiaoCamelLogMessage ebxmlMessageId(final String expression) {
		return set(EBXML_MESSAGE_ID, expression);
	}
	
	public CiaoCamelLogMessage ebxmlMessageId(final Expression expression) {
		return set(EBXML_MESSAGE_ID, expression);
	}
	
	public CiaoCamelLogMessage ebxmlRefToMessageId(final String expression) {
		return set(EBXML_REF_TO_MESSAGE_ID, expression);
	}
	
	public CiaoCamelLogMessage ebxmlRefToMessageId(final Expression expression) {
		return set(EBXML_REF_TO_MESSAGE_ID, expression);
	}
	
	public CiaoCamelLogMessage workflowId(final String expression) {
		return set(WORKFLOW_ID, expression);
	}
	
	public CiaoCamelLogMessage workflowId(final Expression expression) {
		return set(WORKFLOW_ID, expression);
	}
}
