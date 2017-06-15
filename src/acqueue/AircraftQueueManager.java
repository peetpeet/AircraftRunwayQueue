package acqueue;

import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import acqueue.enums.ACSize;
import acqueue.enums.ACUse;
import acqueue.enums.RequestType;

/**
 * This class exposes methods to create and manage an Aircraft queue.
 * 
 * ... Assumptions -  It is assumed concurrency should be handled and in the absence of a larger scope/context 
 * as wells as in the interest of brevity/clarity this class is implemented as a Singleton to prevent any concurrent queue operations.
 * 
 * @author s_karun
 *
 */
public class AircraftQueueManager {

	private static final AircraftQueueManager acQueueManager;
	
	/**
	 * Assumption - In the absence of a more detailed context this queue is reset with all 
	 * existing elements expunged if request type is SYSTEM_BOOT.
	 */
	private PriorityQueue<QueuedAircraft> acQueue; 
	
	static{
		acQueueManager = new AircraftQueueManager();
		acQueueManager.acQueue = new PriorityQueue<QueuedAircraft>();
	}
	
	private  AircraftQueueManager(){}

	public static AircraftQueueManager getAcqueuemanager() {
		return acQueueManager;
	}
	/**
	 * Returns the Enqueued or Dequeued Aircraft for ENEQUEUE & DEQUEUE requests else returns null
	 * @param requestType
	 * @param ac
	 * @return Aircraft
	 */
	public Aircraft aqmRequestProcess(RequestType requestType, Aircraft ac){
		
		if(requestType.equals(RequestType.SYSTEM_BOOT)){
			acQueue.clear();
		}else if(requestType.equals(RequestType.ENQUEUE)){
			this.enqueue(ac);
			return ac;
		}else if(requestType.equals(RequestType.DEQUEUE)){
			return this.dequeue().getAircraft();
		}
		return null;
	}
	
	private boolean enqueue(Aircraft ac){
		
		try{
			return this.acQueue.add(new QueuedAircraft(ac, System.currentTimeMillis()));
			
		}catch(Exception ex){
			// TODO: implement logging framework, until then throw RuntimeException 
			throw new RuntimeException("Something went wrong when Enqueuing an AC !!!");
		}
		
	}
	
	private QueuedAircraft dequeue(){
		
		try{
			if(this.acQueue.isEmpty()){
				throw new RuntimeException(RequestType.DEQUEUE + " request failed. The Queue was empty!");
			}
			return this.acQueue.remove();
			
		}catch(Exception ex){
			// TODO: implement logging framework, until then throw RuntimeException
			throw new RuntimeException("Something went wrong when Dequeuing an AC !!!");
		}
		
	}
	
	/**
	 * Run and Print results of random requests. Set the value of the for loop variable to the desired
	 * number of random requests
	 * 
	 * In keeping with scope implementation of Factory patterns omitted as we expect this will get used in 
	 * an SOA environment preferably using a framework like Spring to leverage IoA facilities.
	 * 
	 * @param args 
	 */
	public static void main(String args[]){
		
		AircraftQueueManager acqManager = AircraftQueueManager.getAcqueuemanager();
		acqManager.aqmRequestProcess(RequestType.SYSTEM_BOOT, null);
		
		/*
		 * Utility Class to generate Random ACs and Requests
		 */
		class GetRandomValues{
			
			ACSize getRandomSize(){
				int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
				for(ACSize size: ACSize.values())
					if(size.getId() == randomNum) return size;
				return null;
			}
			ACUse getRandomUse(){
				int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
				for(ACUse use: ACUse.values())
					if(use.getId() == randomNum) return use;
				return null;
			}
			RequestType getRandomRequest(){
				int randomNum = ThreadLocalRandom.current().nextInt(2, 4);
				for(RequestType request: RequestType.values())
					if(request.getRequestTypeId() == randomNum) return request;
				return null;
			}
		}
		
		GetRandomValues randomUtil = new GetRandomValues();
		for(int i=0; i<20; i++){
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestType randomRequest = randomUtil.getRandomRequest();
			if(acqManager.acQueue.isEmpty() && randomRequest.equals(RequestType.DEQUEUE)){
				i--;
				continue;
			}
			Aircraft ac = new Aircraft(i, randomUtil.getRandomUse(), randomUtil.getRandomSize());
			Aircraft returnedAc = acqManager.aqmRequestProcess(randomRequest, ac);
			
			System.out.println(randomRequest + " " + returnedAc);
		}
	}
}
