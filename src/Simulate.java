
public class Simulate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Airport simulation = new Airport(100);
		System.out.println("=== Simulation Start ===");
		simulation.start();
		System.out.println("=== Simulation Result ===");
		System.out.printf("Average arrivals queue lengths: %3.2f\n", simulation.getTotalArrivalLength() / simulation.getRunTimes());
		System.out.printf("Average departures queue lengths: %3.2f\n", simulation.getTotalDepartureLength() / simulation.getRunTimes());
		System.out.printf("Average arrivals elapsed time: %3.2f\n", simulation.getTotalElapsedArrivalTime() / simulation.getRunTimes());
		System.out.printf("Average departures elapsed time: %3.2f\n", simulation.getTotalElapsedDepartureTime() / simulation.getRunTimes());
		System.out.println("Sucess arrivals: " + simulation.getSuccessLand());
		System.out.println("Success departures: " + simulation.getSuccessTakeoff());
		System.out.println("Total number of crashed: " + simulation.getCrashed());
		System.out.println("Bye~");
	}

}
