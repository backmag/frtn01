import SimEnvironment.*;

// BallAndBeamRegul class to be written by you
public class BallAndBeamRegul extends Thread {
    private AnalogSource analogInPosition;
    private AnalogSource analogInAngle;
    private AnalogSink analogOut;
    private AnalogSink analogRef;

    private ReferenceGenerator refgen;
    private PI picontroller;
    private PID pidcontroller;

    private final static double uMax = 10.0;
    private final static double uMin = -10.0;
    // Nice parameters;
    // Inner:
    // K = 10, H = 0.01
    // Outer:
    // K = -0.2, H = 0.01, Td = 1, N = 5

    // Constructor
    public BallAndBeamRegul(ReferenceGenerator refgen, BallAndBeam bb, int priority) {
        this.refgen = refgen;
        setPriority(priority);
        picontroller = new PI("PI");
        pidcontroller = new PID("PID");
        analogInPosition = bb.getSource(0);
        analogInAngle = bb.getSource(1);
        analogOut = bb.getSink(0);
        analogRef = bb.getSink(1);
    }

    private double limit(double u) {
        if(u > uMax) {
            return uMax;
        } else if (u < uMin) {
            return uMin;
        }
        return u;
    }

    public void run() {
        long t = System.currentTimeMillis();
        while(!Thread.interrupted()) {
            double ref = refgen.getRef();
            double pos = analogInPosition.get();
            double u1 = 0;
            double u2 = 0;
            synchronized (pidcontroller) {
                u1 = limit(pidcontroller.calculateOutput(pos, ref));
                pidcontroller.updateState(u1);
            }
            double angle = analogInAngle.get();
            synchronized (picontroller) {
                u2 = limit(picontroller.calculateOutput(angle,u1));
                analogOut.set(u2);
                picontroller.updateState(u2);
            }
            analogRef.set(ref);
            t = t + picontroller.getHMillis();  // Doesn't matter which controller we use since h is the same. (?)
            long duration = t - System.currentTimeMillis();
            if(duration > 0) {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}