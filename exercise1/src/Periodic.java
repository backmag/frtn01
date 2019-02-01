public class Periodic extends Thread {
    private int period;

    public Periodic(int period) {
        this.period = period;
    }


    public void run() {
        try {
            setPriority(Thread.NORM_PRIORITY + 1);
            System.out.println("Priority: " + getPriority());
            while (!Thread.interrupted()) {
                System.out.print(period);
                System.out.print(", ");
                Thread.sleep(period);
            }
        }
        catch (InterruptedException e) {
        }
        System.out.print("Thread stopped.");
    }

    public static void main(String[] args) {

        Periodic[] p = new Periodic[5];
        p[0] = new Periodic(1000);
        p[1] = new Periodic(500);
        p[2] = new Periodic(200);
        p[3] = new Periodic(400);
        p[4] = new Periodic(2000);
        for(String arg : args) {
            Periodic p2 = new Periodic(Integer.parseInt(arg));
            p2.start();
        }
        int threadCount = Thread.activeCount();
        System.out.printf("%s threads are running. ", threadCount);
        for(Periodic per : p) {
           // per.start();
        }
    }
}