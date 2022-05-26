package ru.otus;

// javac need "-parameters"

public class Example {
    public static void main(String[] args) {
        System.out.println("main");

        var c = IoC.getCalculation();

        c.calculation(1);
        c.calculation("XXX");
        c.calculation(2,3);
        c.calculation(4,5,"test");
    }
}
