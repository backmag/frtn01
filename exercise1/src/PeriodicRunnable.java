public class PeriodicRunnable extends Base implements Runnable {
    private int period;

    public PeriodicRunnable(int period) {
        this.period = period;
    }

    public void run() {
        try{
            Thread.currentThread().setPriority(Thread.NORM_PRIORITY + 1);
            System.out.println("Priority: " + Thread.currentThread().getPriority());
            while(!Thread.interrupted()) {
                System.out.println("Sleeping for " + period + " ms.");
                Thread.sleep(period);
                System.out.println("Thread woke up!");
            }
        } catch (InterruptedException e) {

        }
    }
    public static void main(String[] args) {
        PeriodicRunnable p = new PeriodicRunnable(500);
        Thread t = new Thread(p);
        t.start();
    }
}
