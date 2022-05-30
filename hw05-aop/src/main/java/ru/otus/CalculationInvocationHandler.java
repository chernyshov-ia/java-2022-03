package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class CalculationInvocationHandler implements InvocationHandler {

    final private Calculation calculation;
    final private Set<Method> methods;

    private Set<Method> findMethods(Calculation calculation) {
        var annotated = new HashSet<Method>();

        for (var method : calculation.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Log.class)) {
                try {
                    var m = Calculation.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    annotated.add(m);
                    System.out.println("find annotated method -> " + method);
                } catch (NoSuchMethodException E) {
                    throw new RuntimeException(E);
                }
            }
        }

        for (var method : Calculation.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Log.class)) {
                annotated.add(method);
                System.out.println("find annotated method -> " + method);
            }
        }

        System.out.println();

        return annotated;
    }

    public CalculationInvocationHandler(Calculation calculation) {
        this.calculation = calculation;
        this.methods = findMethods(calculation);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("executing method -> " + method);

        Object o = method.invoke(calculation, args);

        if (methods.contains(method)) {
            var sj = new StringJoiner(", ", "executed method: ", "");
            sj.add(method.getName());
            var parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                sj.add(parameters[i].getName() + ": " + args[i]);
            }
            System.out.println(sj);
        } else {
            System.out.println("this method not annotated");
        }

        System.out.println();

        return o;
    }
}
