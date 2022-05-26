package ru.otus;

public class CalculationImpl implements Calculation {

    public void calculation(int param) {
        System.out.println("calculation1(" + param + ")");
    }

    public void calculation(String param) {
        System.out.println("calculation2(" + param + ")");
    }

    public void calculation(int param1, int param2) {
        System.out.println("calculation3(" + param1 + ", " +  param2 + ")");
    }

    public void calculation(int param1, int param2, String param3) {
        System.out.println("calculation4(" + param1 + ", " + param2 + ", \"" + param3 +"\")");
    }
}
