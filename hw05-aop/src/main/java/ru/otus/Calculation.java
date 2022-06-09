package ru.otus;

public interface Calculation {
    void calculation(int param);
    @Log void calculation(String param);
    @Log void calculation(int param1, int param2);
    void calculation(int param1, int param2, String param3);
}
