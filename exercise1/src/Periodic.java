public class Periodic extends Thread {
    private int period;

    public Periodic(int period) {
        this.period = period;
    }


    public static void main(String[] args) {
        Periodic[] p = new Periodic[5];
        p[0] = new Periodic(1000);
        p[1] = new Periodic(500);
        p[2] = new Periodic(200);
        p[3] = new Periodic(400);
        p[4] = new Periodic(2000);
        for(Periodic per : p) {
            per.start();
        }
    }

    public void run() {
        try {
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
}