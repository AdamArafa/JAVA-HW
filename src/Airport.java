import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Airport {
	
	final static int arrivalMin = 0;
	final static int arrivalMax = 5;
	final static int fuelMin = 1;
	final static int fuelMax = 25;
	
	private int simulateTimes;
	private int crashed = 0;
	private int successLand = 0;
	private int successTakeoff = 0;
	private int runTimes = 1;
	
	Airport() {
        this.simulateTimes = 1000;
    }

    Airport(int simulateTimes) {
        this.simulateTimes = simulateTimes;
    }
	
    private int RandomInRange(int min, int max) {
    	Random randomNumbers = new Random();
		return min + randomNumbers.nextInt(max - min);
    }
    
    private void takeOff(DepartingPlane flight)
    {
    	// let it go~
    	System.out.println("Let " + flight.getFlightNo() + " go~");
    	this.incSuccessTakeoff();
    }
    
	public void start() {
		
		PriorityQueue<ArrivingPlane> arrivingList = new PriorityQueue<ArrivingPlane>(simulateTimes);
		LinkedList<DepartingPlane> departingList = new LinkedList<DepartingPlane>();
		AtomicInteger UUID = new AtomicInteger();

        while ((runTimes <= simulateTimes) || !arrivingList.isEmpty()) {
        	System.out.println("time: " + runTimes);
        	// Between 0 and 5 airplanes may arrive per time interval
        	for (int i = 0; runTimes <= simulateTimes && i < RandomInRange(arrivalMin, arrivalMax); ++i) {
        		arrivingList.offer(new ArrivingPlane(UUID.incrementAndGet(), RandomInRange(fuelMin, fuelMax)));
        		
        	}
        	// print queue
        	/*
        	for (ArrivingPlane flight : arrivingList) {
        		System.out.print(flight.getFlightNo() + "");
        		System.out.println();
        	}
        	*/
        	// The single runway can accommodate up to two events
        	for (int i = 0; i < 2; ++i) {
        		for (int j = 0; j < arrivingList.size(); ++j) {
        			// crashed!
            		if (arrivingList.peek().getFuel() <= 0) {
            			arrivingList.poll();
            			System.out.println("Plane has run out of fuel and crashed ha ha!");
            			this.incCrashed();
            		}
        		}
    			if (!arrivingList.isEmpty()) {
	        		if (arrivingList.peek().getFuel() < 2 || departingList.isEmpty()) {
	        			ArrivingPlane flight = arrivingList.poll();
	        			System.out.println(flight.getFlightNo() + " arrived, fuel: " + flight.getFuel());
	        			this.incSuccessLand();
	        			departingList.add((new DepartingPlane(flight.getFlightNo())));
	        		}
    			}
        		else if (!departingList.isEmpty()) {
        			takeOff(departingList.poll());
        		}
        		else {
        			// Do noting...
        		}
    		}

        	// sub fuel and inc time
        	for (ArrivingPlane flight : arrivingList) {
        		flight.incWaitTime();
        		flight.decFuel();
        		
        	}
        	// add time unit
        	++runTimes;
        }
        // all the remaining flights will land and take off until there is no flight left for processing 
        while (!departingList.isEmpty()) {
        	System.out.println("times: " + runTimes);
        	for (int i = 0; i < 2; ++i) {
        		if (!departingList.isEmpty()) {
        			takeOff(departingList.poll());
        			
        		}
        	}
        	++runTimes;
        }
        
	}

	public int getCrashed() {
		return crashed;
	}

	public void setCrashed(int crashed) {
		this.crashed = crashed;
	}

	public void incCrashed() {
		this.crashed++;
	}
	
	public int getSuccessLand() {
		return successLand;
	}

	public void setSuccessLand(int successLand) {
		this.successLand = successLand;
	}
	
	public void incSuccessLand() {
		this.successLand++;
	}
	
	public int getSuccessTakeoff() {
		return successTakeoff;
	}

	public void setSuccessTakeoff(int successTakeoff) {
		this.successTakeoff = successTakeoff;
	}
	
	public void incSuccessTakeoff() {
		this.successTakeoff++;
	}
}
