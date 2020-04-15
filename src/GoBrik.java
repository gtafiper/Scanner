import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class GoBrik {
	
	

	public void go() {
		
		EV3 brick = LocalEV3.get();
		
		try(EV3LargeRegulatedMotor Left = new EV3LargeRegulatedMotor(brick.getPort("D"));
				EV3LargeRegulatedMotor Right = new EV3LargeRegulatedMotor(brick.getPort("A"));
				EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(brick.getPort("S4"))){
			
			MovePilot pilot = setupPilot(Left, Right);
			RangeFinderAdapter rangeFinder = new RangeFinderAdapter(sensor);
			
			Behavior b1 = new Driver(pilot);
			Behavior b2 = new Sensor(pilot, rangeFinder);
			Behavior b3 = new StopBehavior(pilot);
			Arbitrator arbitrator = new Arbitrator(new Behavior[] {b1, b2, b3});
			
			arbitrator.go();
		
			
			
		
		}

	}
	
	
	 public MovePilot setupPilot(EV3LargeRegulatedMotor wheelLeft, EV3LargeRegulatedMotor wheelRight)
	    {
		Wheel wheel1 = WheeledChassis.modelWheel(wheelLeft, MovePilot.WHEEL_SIZE_EV3).offset(-8);
		Wheel wheel2 = WheeledChassis.modelWheel(wheelRight, MovePilot.WHEEL_SIZE_EV3).offset(8);

		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		
		MovePilot pilot = new MovePilot(chassis);
		double maxspeed = pilot.getMaxLinearSpeed();
		pilot.setLinearAcceleration(maxspeed);
		return pilot;
		 
	    }
}
