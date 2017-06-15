package acqueue.enums;

public enum ACUse {

	PASSENGER("Passenger", 1),
	CARGO("Cargo", 2);
	
	private final String acUse;
	private final int id;
	
	ACUse(String use, int id){
		this.acUse = use;
		this.id = id;
	}
	
	public String toString(){
		return acUse;
	}
	
	public int getId(){
		return id;
	}
}
