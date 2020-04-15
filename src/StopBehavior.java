import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.Sound;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class StopBehavior implements Behavior, KeyListener{
	
	private final MovePilot _pilot;
	private boolean _stop;

	public StopBehavior(MovePilot pilot) {
		_pilot = pilot;
		_stop = false;
		Button.ESCAPE.addKeyListener(this);
	}

	@Override
	public void keyPressed(Key k) {
		_stop = true;
		
	}

	@Override
	public void keyReleased(Key k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean takeControl() {
		return _stop;
	}

	@Override
	public void action() {
		_pilot.stop();
		Sound.buzz();
		System.exit(0);
		
	}

	@Override
	public void suppress() {
		
		
	}

}
