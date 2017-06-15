package acqueue;

import acqueue.enums.ACSize;
import acqueue.enums.ACUse;

/**
 * A Wrapper for Aircraft that captures queuing related info when an Aircraft
 * is added to a queue to assist in queue management, such as the order in which
 * Aircraft were added in the queue. 
 * ... Presumably there are other approaches to track order of addition to queue such as using an 
 *     array to keep track of order of arrival but each could have their drawbacks such as 
 *     having to manage array size in this case.
 * 
 * @author s_karun
 *
 */
public class QueuedAircraft implements Comparable<QueuedAircraft>{

	Aircraft aircraft;
	long timeACQueued; //Enables FIFO implementation for Queue
	
	QueuedAircraft(Aircraft ac, long timeQueued){
		this.aircraft = ac;
		this.timeACQueued = timeQueued;
	}

	public long getTimeACQueued() {
		return timeACQueued;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	/*
	 * Exercised some latitude here to swap return values of -1 and 1 to cater for the natural sorting in PriorityQueue
	 * where the least value appears at the head of the queue and is removed by invoking the remove() method.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(QueuedAircraft qac2) {
		
		Aircraft ac1 = this.getAircraft();
		Aircraft ac2 = qac2.getAircraft();
		
		if(ac1.getAcUse().equals(ACUse.CARGO) && ac2.getAcUse().equals(ACUse.PASSENGER)){
			return 1;
			
		}else if(ac2.getAcUse().equals(ACUse.CARGO) && ac1.getAcUse().equals(ACUse.PASSENGER)){
			return -1;
			
		}else if(ac1.getAcUse().equals(ac2.getAcUse())){
			
			if(ac1.getAcSize().equals(ACSize.SMALL) && ac2.getAcSize().equals(ACSize.LARGE)){
				return 1;
			}else if(ac1.getAcSize().equals(ac2.getAcSize())){
				
				if(this.getTimeACQueued() > qac2.getTimeACQueued()){
					return 1; // ac1 was added to queue AFTER ac2
				}else if (this.getTimeACQueued() == qac2.getTimeACQueued()) { 
					return 0; // ac1 & ac2 are EXACTLY the same priority
				}else{
					return -1; // ac1 was added to queue BEFORE ac2
				}
			}else{
				return -1; // ac1 is of size LARGE
			}
		}else{
			return -1; // ac1 is of type PASSENGER
		}
		
	}
}
