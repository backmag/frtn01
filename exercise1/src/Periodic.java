public class Periodic extends Thread {
    private int period;
    private Screen screen;

    public Periodic(int period, Screen s) {
        this.period = period;
        screen = s;
    }


    public void run() {
        try {
            setPriority(Thread.NORM_PRIORITY + 1);
            System.out.println("Priority: " + getPriority());
                while (!Thread.interrupted()) {
                    synchronized (this) {
                        screen.writePeriod(period);
                        Thread.sleep(period);
                    }
                }
        }
        catch (InterruptedException e) {
        }
        System.out.print("Thread stopped.");
    }

    public static void main(String[] args) {
        Screen s = new Screen();

        Periodic[] p = new Periodic[5];
        p[0] = new Periodic(1000,s);
        p[1] = new Periodic(500,s);
        p[2] = new Periodic(200,s);
        p[3] = new Periodic(400,s);
        p[4] = new Periodic(2000,s);
        for(String arg : args) {
            Periodic p2 = new Periodic(Integer.parseInt(arg),s);
            p2.start();
        }
        int threadCount = Thread.activeCount();
        System.out.printf("%s threads are running. ", threadCount);

        for(Periodic per : p) {
            per.start();
        }
    }
}