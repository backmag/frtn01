// Code skeleton for the SquareWave class in the Buttons exercise

public class SquareWave extends Thread {
	private Regul regul;
	private int sign;
	
	private AmplitudeMonitor ampMon;
	
	// Internal AmplitudeMonitor class
	// Constructor not necessary
	private class AmplitudeMonitor {
		private double amp = 0.0;
		
		// Synchronized access methods. The amplitude should always be non-negative.
		public synchronized double getAmp();
		public synchronized void setAmp(double amp);
	}
	
	// Constructor
	public SquareWave(Regul regul, int priority);
	
	// Public methods to decrease and increase the amplitude by delta. Called from Buttons
	// Should be synchronized on ampMon. Should also call the setRef method in Regul
	public void incAmp(double delta);
	public void decAmp(double delta);
	
	// run method
	public void run();
}

