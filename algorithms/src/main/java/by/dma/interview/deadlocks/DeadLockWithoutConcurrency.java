package by.dma.interview.deadlocks;

/**
 *  Deadlock without synchronized and java.util.concurrent.
 */
public class DeadLockWithoutConcurrency {
    static class A {
        static final B b = new B();
    }

    static class B {
        static final A a = new A();
    }

    public static void main(String[] args) {
        new Thread(A::new).start();
        new B();
    }
}
