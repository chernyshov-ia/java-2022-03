package ru.otus;

import java.lang.reflect.Proxy;

public class IoC {
    private IoC() {
        throw new UnsupportedOperationException();
    }

    public static Calculation getCalculation() {
        return (Calculation) Proxy.newProxyInstance(IoC.class.getClassLoader(), new Class<?>[]{Calculation.class},
                new CalculationInvocationHandler(new CalculationImpl()) );
    }
}
