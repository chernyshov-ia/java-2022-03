package homework;

import homework.testing.TestUtils;
import homework.tests.TestClass1;
import homework.tests.TestClass2;
import homework.tests.TestClass3;
import homework.tests.TestClassEmpty;


public class App {
    public static void main(String[] args) {
        // Тесты не запускаются
        TestUtils.runTests(TestClassEmpty.class);

        // Тест проходит
        TestUtils.runTests(TestClass1.class);

        // Один тест не проходит
        TestUtils.runTests(TestClass2.class);

        // Инициализация не проходит, но финализация таки да
        TestUtils.runTests(TestClass3.class);
    }
}
