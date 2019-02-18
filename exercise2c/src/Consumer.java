public class Consumer extends Thread {
    private RingBufferWithSemaphore rb;

    public Consumer(RingBufferWithSemaphore rb) {
        this.rb = rb;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            while (!Thread.interrupted()) {
                System.out.println("Consumer: Attempting to read a message.");
                String msg = (String) rb.get();
                System.out.println("Consumer: Read \"" + msg + "\"");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Consumer stopped. ");
    }
}
