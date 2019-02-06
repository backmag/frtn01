import se.lth.cs.realtime.Semaphore;

public class WrongWithSemaphore extends Thread {
    private volatile int ti = 0;
    private Semaphore mutex;

    public WrongWithSemaphore() {
        mutex = new Semaphore(1);
    }


    public void run() {
        int e;
        int u;
        int loops = 0;

        setPriority(4);

        try {
            e = 10;
            while(!Thread.interrupted()) {
                mutex.take();
                if(ti != 0) {
                    u = e / ti;
                    System.out.print("Kör vi. ti");
                } else {
                    u = 0;
                    System.out.print("Kör vi. u");
                }
                mutex.give();
                loops++;
            }
        } catch (Exception ex) {
            System.out.println("Terminated after " + loops + " iterations. ");
            System.exit(1);
        }
    }
    public void setTi(int newTi) {
        mutex.take();
        ti = newTi;
        mutex.give();
    }
    public static void main(String[] args) {
        WrongWithSemaphore w1 = new WrongWithSemaphore();
        w1.start();
        int i = 0;
        try {
            while(!Thread.interrupted()) {
                w1.setTi(1);
                Thread.sleep(1);
                w1.setTi(2);
                Thread.sleep(2);
                i++;
            }
        } catch (InterruptedException ex) {
            // plz stop
        }
        System.out.println("Thread stopped.");
    }
}
