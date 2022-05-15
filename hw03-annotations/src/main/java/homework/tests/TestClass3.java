package homework.tests;

import homework.testing.annotations.After;
import homework.testing.annotations.Before;
import homework.testing.annotations.Test;

public class TestClass3 {
    @Before
    public void init() {
        System.out.println("*** initialization ***");
        throw new RuntimeException("Oops!");
    }

    @Test
    public void testOne() {
        System.out.println("*** doing testOne");
    }

    @Test
    public void testTwo() {
        System.out.println("*** doing testTwo");
    }

    @After
    public void close() {
        System.out.println("*** closing ***");
    }
}
