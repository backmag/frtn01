public class Producer extends Thread {
    private RingBufferWithSemaphore rb;

    public Producer(RingBufferWithSemaphore rb) {
        this.rb = rb;
    }
    public void run() {
        int i = 0;
        try {
            while (!Thread.interrupted()) {
                i++;
                System.out.println("Producer: Attempting to write msg nbr: " + i);
                rb.put("Msg nbr "+ i);
                System.out.println("Producer: Msg nbr " + i + " written.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Producer stopped.");
    }
}
