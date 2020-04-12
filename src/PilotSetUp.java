import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.MovePilot;

public class PilotSetUp {
	
	 public static void setupPilot(EV3 brick, MovePilot pilot)
	    {
		Wheel wheel1 = WheeledChassis
			.modelWheel(new EV3LargeRegulatedMotor(brick.getPort("A")), MoveController.WHEEL_SIZE_EV3).offset(-8);
		Wheel wheel2 = WheeledChassis
			.modelWheel(new EV3LargeRegulatedMotor(brick.getPort("D")), MoveController.WHEEL_SIZE_EV3).offset(8);

		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		
		double maxspeed = pilot.getMaxLinearSpeed();
		pilot.setLinearAcceleration(maxspeed);
		 
		pilot = new MovePilot(chassis);
		 
		 
	    }

}
