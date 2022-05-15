package homework.tests;

import homework.testing.annotations.After;
import homework.testing.annotations.Before;
import homework.testing.annotations.Test;

public class TestClass2 {
    @Before
    public void init() {
        System.out.println("[" + this + "] *** initialization ***");
    }

    @Test(displayName = "Пример2.Тест1")
    public void testOne() {
        System.out.println("*** doing testOne ***");
        throw new RuntimeException("Oops! testOne with errors!");
    }

    @Test(displayName = "Пример2.Тест2")
    public void testTwo() {
        System.out.println("*** doing testTwo ***");
    }

    @After
    public void close() {
        System.out.println("*** closing ***");
    }
}
