public class PeriodicWithInnerThread extends Base {
    private PeriodicThread innerThread;
    private int period;

    public PeriodicWithInnerThread(int period) {
        this.period = period;
        innerThread = new PeriodicThread();
    }

    public void start() {
        innerThread.start();
    }

    private class PeriodicThread extends Thread {
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.print(period);
                    System.out.print(", ");
                    Thread.sleep(period);
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        for(String arg : args) {
            new PeriodicWithInnerThread(Integer.parseInt(arg)).start();
            System.out.print(" - New - ");
        }
    }
}
