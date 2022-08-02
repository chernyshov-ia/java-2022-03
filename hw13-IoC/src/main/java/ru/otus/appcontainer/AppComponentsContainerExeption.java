package ru.otus.appcontainer;

public class AppComponentsContainerExeption extends RuntimeException {
    public AppComponentsContainerExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public AppComponentsContainerExeption(String message) {
        super(message);
    }
}
