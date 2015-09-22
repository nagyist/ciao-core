package uk.nhs.ciao.logging;

final class CiaoLogParameters {
	private CiaoLogParameters() {
		// Suppress defaullt constructor
	}
	
	public static final String EVENT_NAME = "EventName";
	public static final String FROM_STATE = "FromState";
	public static final String TO_STATE = "ToState";
	public static final String INPUT_DIRECTORY = "InputDirectory";
	public static final String ORIGINAL_FILE_NAME = "OriginalFileName";
	public static final String DOCUMENT_ID = "DocumentId";
	public static final String SENDER_ASID = "SenderASID";
	public static final String RECEIVER_ASID = "ReceiverASID";
	public static final String RECEIVER_ODS_CODE = "ReceiverODSCode";
	public static final String INTERACTION_ID = "InteractionId";
	public static final String DOCUMENT_PROPERTIES = "DocumentProperties";
	public static final String FILE_NAME = "FileName";
}
