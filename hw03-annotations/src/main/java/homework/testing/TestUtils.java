package homework.testing;

import homework.testing.annotations.After;
import homework.testing.annotations.Before;
import homework.testing.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    private TestUtils() {
    }

    private static List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Method> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            Annotation annotation = method.getAnnotation(annotationClass);
            if (annotation != null) {
                list.add(method);
                method.setAccessible(true);
            }
        }
        return list;
    }

    private static List<TestMethod> getTestMethods(Class<?> clazz) {
        List<TestMethod> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                list.add(new TestMethod(annotation.displayName(), method));
                method.setAccessible(true);
            }
        }
        return list;
    }

    private static void finalizeInstance(List<Method> after, Object instance) {
        try {
            for (Method method : after) {
                System.out.println("[" + instance + "] After: doing <" + method.getName() + ">");
                method.invoke(instance);
                System.out.println("[" + instance + "] After: completed <" + method.getName() + ">");
            }
        } catch (Exception e) {
            System.out.println("[" + instance + "] After: " + e.getMessage());
            throw new TestException("Can't finalize object", e);
        }
    }

    private static void prepareInstance(List<Method> before, Object instance) throws Exception {
        for (Method beforeMethod : before) {
            System.out.println("[" + instance + "] Before: doing <" + beforeMethod.getName() + ">");
            beforeMethod.invoke(instance);
            System.out.println("[" + instance + "] Before: completed <" + beforeMethod.getName() + ">");
        }
    }

    private static boolean runInstanceTest(TestMethod testMethod, Object instance) {
        String testName = (testMethod.displayName != null) &&
                !"".equals(testMethod.displayName) ? testMethod.displayName : testMethod.method.getName();
        try {
            System.out.println("[" + instance + "] Test <" + testName + "> running");
            testMethod.method.invoke(instance);
            System.out.println("[" + instance + "] Test <" + testName + "> succeeded");
            return true;
        } catch (Exception e) {
            System.out.println("[" + instance + "] Test <" + testName + "> failed");
            e.printStackTrace();

        }
        return false;
    }

    private static boolean runTest(List<Method> before, List<Method> after, TestMethod testMethod, Constructor<?> constructor) {
        Object instance = newInstance(constructor);

        Exception beforeException = null;
        try {
            prepareInstance(before, instance);
        } catch (Exception e) {
            System.out.println("[" + instance + "] Before: " + e.getCause().getMessage());
            beforeException = e;
        }

        boolean result = false;

        if (beforeException == null) {
            result = runInstanceTest(testMethod, instance);
        }

        finalizeInstance(after, instance);

        if (beforeException != null) {
            throw new TestException("Can't init object", beforeException);
        }

        return result;
    }

    private static Object newInstance(Constructor<?> constructor) {
        Object instance;
        try {
            instance = constructor.newInstance();
        } catch (Exception e) {
            throw new TestException("Can't instantiate object", e);
        }
        return instance;
    }

    private static void showResults(int total, int succeeded, int failed) {
        System.out.printf("Succeeded: %d, failed: %d, total: %d%n", succeeded, failed, total);
    }

    private static Constructor<?> getConstructor(Class<?> clazz) {
        Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new TestException("Can't instantiate object", e);
        }
        return constructor;
    }

    public static void runTests(Class<?> clazz) {
        List<TestMethod> tests = getTestMethods(clazz);
        if (tests.isEmpty()) {
            showResults(0,0,0);
            return;
        }

        Constructor<?> constructor = getConstructor(clazz);
        List<Method> before = getAnnotatedMethods(clazz, Before.class);
        List<Method> after = getAnnotatedMethods(clazz, After.class);

        int total = 0;
        int succeeded = 0;

        try {
            for (TestMethod test : tests) {
                if (runTest(before, after, test, constructor)) {
                    succeeded++;
                }
                total++;
            }
        } catch (Exception e) {
            showResults(total, succeeded, total - succeeded);
            throw new TestException(e.getMessage(), e);
        }

        showResults(total, succeeded, total - succeeded);
    }

    private record TestMethod(String displayName, Method method) {
    }

}
