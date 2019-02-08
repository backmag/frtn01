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
		public synchronized double getAmp() {
			return amp;
		}
		public synchronized void setAmp(double amp) {
			this.amp = Math.max(0,amp);
		}
	}
	
	// Constructor
	public SquareWave(Regul regul, int priority) {
		this.regul = regul;
		setPriority(priority);
		sign = 1;
		ampMon = new AmplitudeMonitor();
	}
	
	// Public methods to decrease and increase the amplitude by delta. Called from Buttons
	// Should be synchronized on ampMon. Should also call the setRef method in Regul
	public void incAmp(double delta) {
		synchronized (ampMon) {
			ampMon.setAmp(ampMon.getAmp() + delta);
		}
	}
	public void decAmp(double delta) {
		synchronized (ampMon) {
			ampMon.setAmp(ampMon.getAmp() - delta);
		}
	}
	private void setRef() {
		regul.setRef(ampMon.getAmp() * sign);
	}
	
	// run method
	public void run() {
		int h = 10000; // Half the time of the period (20s).
		try {
			while(!Thread.interrupted()) {
				sign = -sign;
				setRef();
				Thread.sleep(h);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

