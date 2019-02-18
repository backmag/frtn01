public class Main {

    public static void main(String[] args) {
        RingBufferWithSemaphore rb = new RingBufferWithSemaphore(5);

        Producer p = new Producer(rb);
        Consumer c = new Consumer(rb);

        p.start();
        c.start();
    }
}
