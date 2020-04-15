import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Driver implements Behavior{
	
	private boolean _suppressed;
	private MovePilot _pilot;
	
	
	public Driver(MovePilot pilot) {
		_pilot = pilot;
		_suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		
		_suppressed = false;
		
		 Sound.beep();
		 _pilot.forward();
		 while( !_suppressed )
		      Thread.yield();
		 
		 _pilot.stop();
	
	}
	

	@Override
	public void suppress() {
		_suppressed = true;
		
	}
	

	 
	 
	

}
