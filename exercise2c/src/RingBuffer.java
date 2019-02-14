public class RingBuffer {
    private Object[] elements;
    private int nextRead;
    private int nextWrite;
    private int n;
    private int bufSize;

    public RingBuffer(int bufSize) {
        elements = new Object[bufSize];
        this.bufSize = bufSize;
        n = 0;
        nextRead = 0;
        nextWrite = 0;
    }

    public synchronized Object get() throws InterruptedException {
        if(n == 0) {
            wait();
        }
        Object ret = elements[nextRead];
        elements[nextRead] = null;
        nextRead = (nextRead + 1) % bufSize;
        n--;
        notifyAll();
        return ret;
    }
    public synchronized void put(Object o) throws InterruptedException {
        if(n == bufSize) {
            wait();
        }
        elements[nextWrite] = o;
        nextWrite = (nextWrite + 1) % bufSize;
        n++;
        notifyAll();
    }
}
