public class PID {
    // Current PID parameters
    private PIDParameters p;

    private double e;       // Error
    private double v;       // Desired control signal
    private double D;       // Derivative state
    private double I;       // Integrator state
    private double yOld;    // Previous y-value
    private double ad;      // Constant for calculating D
    private double bd;      // Constant for calculating D

    // Constructor
    public PID(String name) {
        p = new PIDParameters();
        p.K = -0.1;
        p.Ti = 0.0;
        p.Td = 0.1;
        p.Tr = 0.1;
        p.N = 0.1;
        p.Beta = 0.1;
        p.H = 0.1;
        p.integratorOn = false;
        setParameters(p);
        new PIDGUI(this, p, name);
        I = 0.0;
        e = 0.0;
        v = 0.0;
        D = 0.0;
    }

    // Calculates the control signal v.
    // Called from BallAndBeamRegul.
    public synchronized double calculateOutput(double y, double yref) {
        e = yref - y;
        D = D*ad - bd*(y-yOld);
        v = p.K*(p.Beta*yref - y) + I + D;
        yOld = y;
        return v;
    }

    // Updates the controller state.
    // Should use tracking-based anti-windup
    // Called from BallAndBeamRegul.
    public synchronized void updateState(double u) {
        if(p.integratorOn) {
            I = I + (p.K * p.H / p.Ti) * e + (p.H / p.Ti) * (u - v);
        } else {
            I = 0.0;
        }
    }

    // Returns the sampling interval expressed as a long.
    // Explicit type casting needed.
    public synchronized long getHMillis() {
        return (long) (p.H * 1000);
    }

    // Sets the PIDParameters.
    // Called from PIDGUI.
    // Must clone newParameters.
    public synchronized void setParameters(PIDParameters newParameters) {
        p = (PIDParameters) newParameters.clone();
        if(!p.integratorOn) {
            I = 0.0;
        }
        ad = p.Td/(p.Td + p.N*p.H);
        bd = p.K*p.N*ad;
    }
}