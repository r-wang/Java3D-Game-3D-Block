package blocksGame;

import java.util.Enumeration;
import javax.media.j3d.*;

/**
 * TimeBehavior is a subclass of Behavior. It changes the scene of the
 * application with time
 * 
 * @author qiqi
 * 
 */

public class TimeBehavior extends Behavior {
	private WakeupCondition timeOut;
	private int timeDelay;
	private WrapBlocksGame wbg;

	/**
	 * Constructs a TimeBehavior class with the specified parameters
	 * 
	 * @param td
	 *            Time delay
	 * @param w
	 *            Instance of WrapBlocksGame to be effected
	 */
	public TimeBehavior(int td, WrapBlocksGame w) {
		timeDelay = td;
		wbg = w;
		timeOut = new WakeupOnElapsedTime(timeDelay);
	}

	/**
	 * Specifies the condition under which the behavior is initizlized
	 */
	public void initialize() {
		wakeupOn(timeOut);
	}

	/**
	 * Defines the actions to be taken when stimulated
	 */
	public void processStimulus(Enumeration criteria) {

		wbg.setRemainingTime(wbg.getRemainingTime() - 1);

		wbg.updateText();

		wakeupOn(timeOut);
	}

} // end of TimeBehavior class
