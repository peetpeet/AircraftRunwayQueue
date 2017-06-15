package acqueue.enums;

public enum ACSize {

	LARGE("Large",1),
	SMALL("Small",2);
	
	private final String acSize;
	private final int id;
	
	ACSize(String size, int id){
		this.acSize = size;
		this.id = id;
	}
	
	public String toString(){
		return acSize;
	}
	
	public int getId(){
		return id;
	}
}
