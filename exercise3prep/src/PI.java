public class PI {
    // Current PI parameters
    private PIParameters p;
    private double I;   // Integrator state
    private double v;   // Desired control signal
    private double e;   // Current control error

    // Constructor
    public PI(String name) {
        p = new PIParameters();
        p.K = 1;
        p.Ti = 0;
        p.Tr = 0;
        p.Beta = 1;
        p.H = 0.1;
        p.integratorOn = false;
        setParameters(p);
        new PIGUI(this,p,name);
        I = 0.0;
        v = 0.0;
        e = 0.0;
    }

    // Calculates the control signal v.
    // Called from BeamRegul.
    public synchronized double calculateOutput(double y, double yref) {
        e = yref - y;
        v = p.K*(p.Beta*yref - y) + I;
    return v;
    }
    // Updates the controller state.
    // Should use tracking-based anti-windup
    // Called from BeamRegul.
    public synchronized void updateState(double u) {
        if(p.integratorOn) {
            I = I + (p.K * p.H / p.Ti) * e + (p.H / p.Ti) * (u - v);
        } else {
            I = 0.0;
        }
    }


    // Returns the sampling interval expressed as a long.
    // Note: Explicit type casting needed
    public synchronized long getHMillis() {
        return (long) (p.H * 1000.0);
    }

    // Sets the PIParameters.
    // Called from PIGUI.
    // Must clone newParameters.
    public synchronized void setParameters(PIParameters newParameters) {
        p = (PIParameters) newParameters.clone();
        if(!p.integratorOn) {
            I = 0.0;
        }
    }
}