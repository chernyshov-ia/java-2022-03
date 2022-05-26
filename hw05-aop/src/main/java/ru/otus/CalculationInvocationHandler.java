package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CalculationInvocationHandler implements InvocationHandler {

    final private Calculation calculation;

    public CalculationInvocationHandler(Calculation calculation) {
        this.calculation = calculation;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = method.invoke(calculation, args);

        if (method.isAnnotationPresent(Log.class)) {
            var sb = new StringBuilder("executed method: ");
            sb.append(method.getName());
            var parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                sb.append(", ");
                sb.append(parameters[i].getName());
                sb.append(": ");
                sb.append(args[i]);
            }
            System.out.println(sb);
        }
        return o;
    }
}
