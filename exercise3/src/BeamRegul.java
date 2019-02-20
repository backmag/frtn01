import SimEnvironment.*;

// BeamRegul class to be written by you
public class BeamRegul extends Thread {
    // IO interface declarations
    private AnalogSource analogIn;
    private AnalogSink analogOut;
    private AnalogSink analogRef;
    private ReferenceGenerator refgen;
    private PI controller;

    private double uMin = -10.0;
    private double uMax = 10.0;

    public BeamRegul(ReferenceGenerator refgen, Beam beam, int priority) {
        this.refgen = refgen;
        setPriority(priority);
        controller = new PI("PI");
        // Code to initialize the IO
        analogIn = beam.getSource(0);
        analogOut = beam.getSink(0);
        analogRef = beam.getSink(1);
    }
    // Limit the output according to specifications.
    private double limit(double u) {
        if(u < uMin) {
            u = uMin;
        } else if (u > uMax) {
            u = uMax;
        }
        return u;
    }

    public void run() {
        long t = System.currentTimeMillis();
            while (!Thread.interrupted()) {
                double y = analogIn.get();
                double ref = refgen.getRef();
                synchronized (controller) {
                    double u = limit(controller.calculateOutput(y, ref));
                    controller.updateState(u);
                    analogOut.set(u);
                }
                analogRef.set(ref);
                t = t + controller.getHMillis();
                long duration = t - System.currentTimeMillis(); // How can this be < 0 ?
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