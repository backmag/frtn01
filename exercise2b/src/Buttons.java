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
	public Buttons(Regul regul, SquareWave square, int priority, Box b) {
		this.regul = regul;
		this.square = square;
		setPriority(priority);
		onInput = b.getOnButtonInput();
		offInput = b.getOffButtonInput();
		incInput = b.getIncButtonInput();
		decInput = b.getDecButtonInput();

	};

	// run method
	public void run() {
		final int h = 10; // Time in ms between polls.
		final double delta = 10 / (100*60); // Incrementation for each 10ms interval.
				try {
					while(!Thread.interrupted()) {
						if(onInput.get()) {
							regul.turnOn();
						}
						if(offInput.get()) {
							regul.turnOff();
						}
						if(incInput.get()) {
							square.incAmp(delta);
						}
						if(decInput.get()) {
							square.decAmp(delta);
						}
					}
					Thread.sleep(h);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
	}
}
