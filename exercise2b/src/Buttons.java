// Code skeleton for the Buttons class in the Buttons exercise

import SimEnvironment.*;

public class Buttons extends Thread {
	private Regul regul;
	private SquareWave square;

	// Inputs and outputs
	private DigitalButtonIn onInput;
	private DigitalButtonIn offInput;
	private DigitalButtonIn incInput;
	private DigitalButtonIn decInput;

	// Constructor
	public Buttons(Regul regul, SquareWave square, int priority, Box b);

	// run method
	public void run();
}
