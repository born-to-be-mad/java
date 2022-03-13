package by.dma.tricks.exception;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helps to determine why resource was not closed.
 *
 * @implNote Long-lived Closeable objects can have a complex life cycle and ensuring they are close when they need to
 * be can be
 * hard to trace, and can lead to resource leaks.  Some resources are not cleaned up when the GC frees the object e.g
 * . a RandomAccessFile object is cleaned up on a GC by the file it represents isn’t closed unless you close it,
 * leading to a potential resource leak of file handles.
 */
public class StackTraceDemoResourceLeakIdentification {

    static class MyResource implements Closeable {

        private final transient StackTrace createdHere = new StackTrace("Created here");
        volatile transient boolean closed;

        @Override
        public void close() throws IOException {
            closed = true;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            if (!closed) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Resource discarded but not closed", createdHere);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyResource();
        System.gc();
        Thread.sleep(1000);
    }
}
