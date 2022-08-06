package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumbersTask implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger(NumbersTask.class);
        public static final Object LOCK = new Object();
        public static final int MAX_VALUE = 10;
        public static String lastThreadName = "";

        private int number = 1;
        private int delta = 1;

        private void sleep() throws InterruptedException {
            Thread.sleep(200);
        }

        private void doWork() {
            logger.info(String.valueOf(number));

            if (delta > 0 && number == MAX_VALUE) {
                delta = -1;
            } else if (delta < 0 && number == 1) {
                delta = 1;
            }
            number = number + delta;
        }

        @Override
        public void run() {
            try {
                synchronized (LOCK) {
                    while (!Thread.currentThread().isInterrupted()) {
                        while (lastThreadName.equals(Thread.currentThread().getName())) {
                            LOCK.wait();
                        }
                        doWork();
                        lastThreadName = Thread.currentThread().getName();
                        sleep();
                        LOCK.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                logger.info("Interrupted...");
                Thread.currentThread().interrupt();
            }
        }
}
