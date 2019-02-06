public class WrongWithSynchronization extends Thread {
    private volatile int ti = 0;

    public synchronized void run() {
        int e;
        int u;
        int loops = 0;

        setPriority(4);
        try {
            e = 10;
            while(!Thread.interrupted()) {
                    if(ti != 0) {
                        u = e / ti;
                        System.out.println("Running if.");
                    } else {
                        u = 0;
                        System.out.println("Running else.");
                    }
                }
                loops++;
        } catch (Exception ex) {
            System.out.println("Terminated after " + loops + " iterations with " + ex);
            System.exit(1);
        }
    }

    public synchronized void setTi(int ti) {
        this.ti = ti;
    }

    public static void main(String[] args) {
        WrongWithSynchronization w1 = new WrongWithSynchronization();
        w1.start();
        int i = 0;
        try {
            while(!Thread.interrupted()) {
                w1.setTi(10);
                Thread.sleep(1);
                w1.setTi(1);
                Thread.sleep(2);
                i++;
                if(i > 100) {
                    System.out.println("Main thread is alive. i = " + i);
                    i = 0;
                }
            }
        } catch (InterruptedException ex) {

        }
        System.out.println("Thread stopped.");
    }
}
