// Code skeleton for the Regul class in the Buttons exercise

import SimEnvironment.*;

public class Regul extends Thread {
	// Analog inputs and outputs
	private AnalogSource yIn;
	private AnalogSink uOut;
	private AnalogSink rOut;
	
	// Box lamp outputs
	private DigitalButtonOut onButtonLamp;
	private DigitalButtonOut offButtonLamp;
	
	// Internal Monitors
	private ParameterMonitor paramMon;
	private ReferenceMonitor refMon;
	private OnMonitor onMon;
	
	// Constructor
	// Here the internal monitor objects should be created and
	// the inputs and outputs should be initialized.
	public Regul(int priority, Box b, FirstOrderProcess proc);
	
	// Public method to set K. Should not be synchronized.
	public void setK(double K);
	
	// Public method to set the reference. Should not be synchronized.
	public void setRef(double ref);
	
	// Method to check if the controller  is on. Should be private
	// since it is only called from Regul itself.
	private boolean isOn();
	
	// Public methods to turn off and on the controller
	// Should not be synchronized. Should update the button lamps
	public void turnOff();
	public void turnOn();
	
	// Class definition for internal ParameterMonitor
	private class ParameterMonitor {
		private double K = 1.0;
		
		// Synchronized access methods. K should always be non-negative.
		public synchronized double getK();
		public synchronized void setK(double K);
	}
	
	// Class definition for internal ReferenceMonitor
	private class ReferenceMonitor {
		private double ref = 0.0;
		
		// Synchronized access methods
		public synchronized double getRef();
		public synchronized void setRef(double ref);
	}
	
	// Class definition for internal OnMonitor
	private class OnMonitor {
		private boolean on = false;
		
		// Synchronized access methods
		public synchronized boolean isOn();
		public synchronized void setOn(boolean on);
	}
	
	// Run method
	public void run();
}
