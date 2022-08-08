package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumbersHomework {
    private static final Logger logger = LoggerFactory.getLogger(NumbersHomework.class);

    public static void main(String[] args) throws Exception {

        var thread1 = new Thread(new NumbersTask());
        var thread2 = new Thread(new NumbersTask());

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        NumbersTask.lastThreadName = thread2.getName();

        thread2.start();
        thread1.start();

        logger.info("{}", "Starting threads...");

        logger.info("{}", "Running 10 seconds...");

        Thread.sleep(10000);

        logger.info("{}", "Interrupting threads...");

        thread1.interrupt();
        thread2.interrupt();

        logger.info("{}", "Waiting stopping threads...");

        thread1.join();
        thread2.join();

        logger.info("{}", "Threads stopped...");
    }


}
