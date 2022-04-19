package homework.tests;

import homework.testing.annotations.After;
import homework.testing.annotations.Before;
import homework.testing.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestClass1 {
    private List<Integer> list;

    @Before
    public void init() {
        System.out.println("[" + this + "] *** initialization ***");
        list = new ArrayList<>();
    }

    @Before
    public void initAdditional() {
        System.out.println("*** init additional resources *** ");
    }

    @Test
    public void testOne() {
        System.out.println("*** doing testOne ***");
    }

    @Test
    public void testTwo() {
        System.out.println("*** doing testTwo ***");
    }

    @After
    public void close() {
        System.out.println("*** closing ***");
    }
}
