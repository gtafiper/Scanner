import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Sensor implements Behavior{
	
	
	private boolean suppressed = false;
	private static MovePilot pilot = null;
	EV3 brick = (EV3) BrickFinder.getDefault();
	RangeFinderAdapter sencor;
	
	
	
	
	
	
	public Sensor (String port) {
		try(EV3UltrasonicSensor ev3Ultrasonic = new EV3UltrasonicSensor(brick.getPort(port))){
		
		sencor = new RangeFinderAdapter(ev3Ultrasonic.getDistanceMode());
	}
	}
	
	@Override
	public boolean takeControl() {
	return sencor.getRange() < 30;
	}

	@Override
	public void action() {
	suppressed = false;
	 EV3 brick = (EV3) BrickFinder.getDefault();
	 PilotSetUp.setupPilot(brick, pilot);
	 
	 double maxspeed = pilot.getMaxLinearSpeed();
	 pilot.setLinearAcceleration(maxspeed);
	 pilot.travel(-100);
	 pilot.rotate(90 + (int)(Math.random() * 180));
	Sound.buzz();	
	
	 while( pilot.isMoving() && !suppressed )
         Thread.yield();
	 
	 pilot.stop();
	
	}

	@Override
	public void suppress() {
		 suppressed = true;
		
	}
	

	
	
	 
	 
	

}
