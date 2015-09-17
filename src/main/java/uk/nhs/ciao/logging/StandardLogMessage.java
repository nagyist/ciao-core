package uk.nhs.ciao.logging;

public class StandardLogMessage extends ReusableLogMessage {
	@Override
	public StandardLogMessage set(String key, Object value) {
		super.set(key, value);
		return this;
	}
	
	public StandardLogMessage eventName(final Object value) {
		return set("EventName", value);
	}
	
	public StandardLogMessage fromState(final Object value) {
		return set("FromState", value);
	}
	
	public StandardLogMessage toState(final Object value) {
		return set("ToState", value);
	}
	
	public StandardLogMessage inputDirectory(final Object value) {
		return set("InputDirectory", value);
	}
	
	public StandardLogMessage originalFileName(final Object value) {
		return set("OriginalFileName", value);
	}
	
	public StandardLogMessage documentId(final Object value) {
		return set("DocumentId", value);
	}
	
	public StandardLogMessage senderAsid(final Object value) {
		return set("SenderASID", value);
	}
	
	public StandardLogMessage receiverAsid(final Object value) {
		return set("ReceiverASID", value);
	}
	
	public StandardLogMessage receiverODSCode(final Object value) {
		return set("ReceiverODSCode", value);
	}
	
	public StandardLogMessage interactionId(final Object value) {
		return set("InteractionId", value);
	}
}
