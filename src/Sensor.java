import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.RangeFinderAdapter;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Sensor implements Behavior{
	
	
	private boolean suppressed;
	private MovePilot _pilot;
	EV3 brick = (EV3) BrickFinder.getDefault();
	private RangeFinder _sencor;
	
	
	
	
	
	
	public Sensor (MovePilot pilot, RangeFinder sencor) {
		_pilot = pilot;
		_sencor = sencor;
	}
	
	
	@Override
	public boolean takeControl() {
	return _sencor.getRange() < 25;
	}
	
	
	@Override
	public void action() {
	suppressed = false;
	
	 double maxspeed = _pilot.getMaxLinearSpeed();
	 _pilot.setLinearAcceleration(maxspeed);
	 
	 _pilot.travel(-50);
	 
	 _pilot.rotate(90 + (int)(Math.random() * 180));
	Sound.buzz();	
	
	
	 while( _pilot.isMoving() && !suppressed )
         Thread.yield();
	 
	 _pilot.stop();
	
	}
	
	@Override
	public void suppress() {
		 suppressed = true;
		
	}
	

	

	

	
	
	 
	 
	

}
