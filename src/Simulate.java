
public class Simulate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Airport simulation = new Airport(100);
		System.out.println("=== Simulation Start ===");
		simulation.start();
		System.out.println("=== Simulation Result ===");
		System.out.println("Crashed count: " + simulation.getCrashed());
		System.out.println("Sucess Land count: " + simulation.getSuccessLand());
		System.out.println("Success Takeoff count: " + simulation.getSuccessTakeoff());
		System.out.println("bye~");
	}

}
