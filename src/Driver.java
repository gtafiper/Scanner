import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Driver implements Behavior{
	
	private boolean suppressed = false;
	private static MovePilot pilot = null;

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		
		 suppressed = false;
		 EV3 brick = (EV3) BrickFinder.getDefault();
		 PilotSetUp.setupPilot(brick, pilot);
		 Sound.beep();
		 pilot.forward();
		 while( !suppressed )
		      Thread.yield();
		 
		 pilot.stop();
	
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}
	

	 
	 
	

}
