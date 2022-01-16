package by.dma.task;

public class Demo {

    public static void main(String[] args) {
        while(true) {
            new ThreadTask().start();
            new Thread(new RunnableTask()).start();
            new Thread(() -> System.out.println("Lambda")).start();
            System.out.println("main");
        }
    }
}

class RunnableTask implements Runnable {

    @Override
    public void run() {
        System.out.println("RunnableTask");
    }
}


class ThreadTask extends Thread {

    @Override
    public void run() {
        System.out.println("ThreadTask");
    }
}
