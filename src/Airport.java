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
	
	private double totalArrivalLength = 0;
	private double totalDepartureLength = 0;
	private double totalElapsedArrivalTime = 0;
	private double totalElapsedDepartureTime = 0;
	
	private int runTimes = 0;
	
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
    	System.out.println("NO." + flight.getFlightNo() + " departed.");
    	this.incSuccessTakeoff();
    }
    
	public void start() {
		
		PriorityQueue<ArrivingPlane> arrivingList = new PriorityQueue<ArrivingPlane>(simulateTimes);
		LinkedList<DepartingPlane> departingList = new LinkedList<DepartingPlane>();
		AtomicInteger UUID = new AtomicInteger();

        while ((runTimes <= simulateTimes) || !arrivingList.isEmpty()) {
        	int currentSuccessLand = 0, currentSuccessTakeoff = 0, currentCrashed = 0;
        	// the current time interval number
        	System.out.println("Time: " + runTimes);
        	// Between 0 and 5 airplanes may arrive per time interval
        	for (int i = 0; runTimes <= simulateTimes && i < RandomInRange(arrivalMin, arrivalMax); ++i) {
        		arrivingList.offer(new ArrivingPlane(UUID.incrementAndGet(), RandomInRange(fuelMin, fuelMax)));
        		
        	}
        	// The single runway can accommodate up to two events
        	for (int i = 0; i < 2; ++i) {
        		for (int j = 0; j < arrivingList.size(); ++j) {
        			// crashed!
            		if (arrivingList.peek().getFuel() <= 0) {
            			System.out.println("Plane " + arrivingList.poll().getFlightNo() + " run out of fuel and crashed ha ha!");
            			this.incCrashed();
            			++currentCrashed;
            		}
        		}
    			if (!arrivingList.isEmpty()) {
	        		if (arrivingList.peek().getFuel() < 2 || departingList.isEmpty()) {
	        			ArrivingPlane flight = arrivingList.poll();
	        			System.out.println("NO." + flight.getFlightNo() + " arrived.");
	        			this.incSuccessLand();
	        			departingList.add((new DepartingPlane(flight.getFlightNo())));
	        			++currentSuccessLand;
	        		}
    			}
        		else if (!departingList.isEmpty()) {
        			takeOff(departingList.poll());
        			++currentSuccessTakeoff;
        		}
        		else {
        			// Do noting...
        		}
    		}
        	//// Data Displays ////
        	// the landing queue size
        	System.out.println("landing queue size: " + arrivingList.size());
        	// identifier of each flight in the landing queue and its available fuel time
    		System.out.println("landing queue:");
        	for (ArrivingPlane flight : arrivingList) {
        		System.out.print("NO." + flight.getFlightNo() + ":" + flight.getFuel() + " ");
        	}
    		System.out.println();
    		// the departing queue size
    		System.out.println("departing queue size: " + departingList.size());
    		// identifier of each flight in the departing queue
    		System.out.println("departing queue:");
        	for (DepartingPlane flight : departingList) {
        		System.out.print("NO." + flight.getFlightNo() + " ");
        	}
    		System.out.println();
    		// total number of successful landings during the interval
    		System.out.println("Current successful landing: " + currentSuccessLand);
    		// total number of successful departures for the interval
    		System.out.println("Current successful departure: " + currentSuccessTakeoff);
    		// the number of crashes occurring in this interval
    		System.out.println("Current successful crashed: " + currentCrashed);
    		System.out.println("======//////======");
    		//// END OF DISAPLY ////
    		
        	// sub fuel and inc time
        	for (ArrivingPlane flight : arrivingList) {
        		flight.incWaitTime();
        		flight.decFuel();
        		
        	}
        	// inc departure wait time
        	for (DepartingPlane flight : departingList) {
        		flight.incWaitTime();
        		
        	}
        	// add time unit
        	++runTimes;
        }
        // all the remaining flights will land and take off until there is no flight left for processing 
        while (!departingList.isEmpty()) {
        	System.out.println("Time: " + runTimes);
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
