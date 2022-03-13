package by.dma.tricks.exception;

import java.io.Closeable;

/**
 * Records the cause of an Exception which may be thrown later
 */
public class StackTraceAsDefferedExceptionDemo {

    static class MyCloseable implements Closeable {

        protected transient volatile StackTrace closedHere;

        @Override
        public void close() {
            closedHere = new StackTrace("Closed here");
        }

        public void useThis() {
            if (closedHere != null) {throw new IllegalStateException("Closed", closedHere);}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyCloseable mc = new MyCloseable();
        Thread t = new Thread(mc::close, "closer");
        t.start();
        t.join();
        mc.useThis();
    }
}
