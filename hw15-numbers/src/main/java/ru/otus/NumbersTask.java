package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumbersTask implements Runnable {
        private static final Logger logger = LoggerFactory.getLogger(NumbersTask.class);
        public static final int MAX_VALUE = 10;
        public static String lastThreadName = "";

        private int number = 1;
        private int delta = 1;

        private static void sleep() throws InterruptedException {
            Thread.sleep(200);
        }

        private void doWork() {
            logger.info("Number = {}", number);

            if (delta > 0 && number == MAX_VALUE) {
                delta = -1;
            } else if (delta < 0 && number == 1) {
                delta = 1;
            }
            number = number + delta;
        }

        public static synchronized void doRun(NumbersTask task) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    while (lastThreadName.equals(Thread.currentThread().getName())) {
                        NumbersTask.class.wait();
                    }
                    task.doWork();
                    lastThreadName = Thread.currentThread().getName();
                    sleep();
                    NumbersTask.class.notifyAll();
                }
            } catch (InterruptedException e) {
                logger.info("{}", "Interrupted...");
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void run() {
            doRun(this);
        }
}
