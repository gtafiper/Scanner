import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	
	public static void main(String[] args) {
		Behavior b1 = new Driver(); 
		Behavior b2 = new Sensor("s4");
		Behavior [] bArray = {b1, b2};
	      Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
 
	}
	
		
}
