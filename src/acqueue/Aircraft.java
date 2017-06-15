package acqueue;

import acqueue.enums.ACSize;
import acqueue.enums.ACUse;

/**
 * Represents a Large or Small Passenger or Cargo Aircraft.
 * ... TODO: Might be a better approach to implement these 2 types as interfaces but in the interest 
 * ... of brevity/clarity we will instead represent these as properties of the Aircraft. 
 * 
 * @author s_karun
 *
 */
public class Aircraft {
	
	int acId;
	ACUse acUse;
	ACSize acSize;
	
	Aircraft(int id, ACUse use, ACSize size){
		
		this.acId = id;
		this.acUse = use;
		this.acSize = size;
	}
	
	public ACUse getAcUse() {
		return acUse;
	}
	public void setAcUse(ACUse acUse) {
		this.acUse = acUse;
	}
	public ACSize getAcSize() {
		return acSize;
	}
	public void setAcSize(ACSize acSize) {
		this.acSize = acSize;
	}
	public int getAcId() {
		return acId;
	}
	
	public String toString(){
		
		return this.acId + " " + this.acUse + " " + this.acSize; 
	}
}
