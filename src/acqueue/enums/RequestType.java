package acqueue.enums;

public enum RequestType {

	SYSTEM_BOOT("System Boot",1),
	ENQUEUE("Enqueue",2),
	DEQUEUE("Dequeue",3);
	
	private final String requestType;
	private final int requestTypeId;
	
	private RequestType(String type, int typeId){
		this.requestType = type;
		this.requestTypeId = typeId;
	}

	public String getRequestType() {
		return requestType;
	}

	public int getRequestTypeId() {
		return requestTypeId;
	}
	
	public String toString(){
		return this.requestTypeId + " " + this.requestType;
	}
}
