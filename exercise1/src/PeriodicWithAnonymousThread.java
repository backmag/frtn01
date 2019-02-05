public class PeriodicWithAnonymousThread extends Base {
    private int period;
    private Thread t;

    public PeriodicWithAnonymousThread(int period) {
        this.period = period;
        t = new Thread() {
            public void run() {
                try {
                    Thread.currentThread().setPriority(Thread.NORM_PRIORITY + 1);
                    System.out.println("Priority: " + Thread.currentThread().getPriority());
                    while (!Thread.interrupted()) {
                        System.out.println("Sleeping for " + period + " ms.");
                        Thread.sleep(period);
                        System.out.println("Thread woke up!");
                    }
                } catch (InterruptedException e) {
                }
            }
        };
    }
    public void start() {
        t.start();
    }

    public static void main(String[] args) {
        for(String arg : args) {
            PeriodicWithAnonymousThread t = new PeriodicWithAnonymousThread(Integer.parseInt(arg));
            t.start();
        }
    }
}
