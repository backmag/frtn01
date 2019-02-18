import se.lth.control.realtime.*;

import java.util.concurrent.locks.Condition;

public class RingBufferWithSemaphore {
    private final int bufSize;
    private int nextRead;
    private int nextWrite;
    private Object[] elements;
    private Semaphore sem;
    private int n;
    private ConditionVariable nonEmpty;
    private ConditionVariable nonFull;


    public RingBufferWithSemaphore(int bufSize) {
        this.bufSize = bufSize;
        sem = new Semaphore(bufSize);
        elements = new Object[bufSize];
        sem = new Semaphore(1);
        nonEmpty = new ConditionVariable(sem);
        nonFull = new ConditionVariable(sem);
    }

    public Object get() {
        sem.take();
            while (n == 0) {
                nonEmpty.cvWait();
            }
            Object ret = elements[nextRead];
            elements[nextRead] = null;
            nextRead = (nextRead + 1) % bufSize;
            n--;
            nonFull.cvNotifyAll();
            sem.give();
            return ret;
    }

    public void put(Object o) {
        sem.take();
        while(n == bufSize) {
            nonFull.cvWait();
        }
        elements[nextWrite] = o;
        n++;
        nextWrite = (nextWrite + 1) % bufSize;
        nonEmpty.cvNotifyAll();
        sem.give();
    }
}
