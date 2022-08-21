package ru.otus;

import ru.otus.client.GRPCNumbersClientImpl;
import ru.otus.client.NumbersClient;

public class MyGRPCClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws Exception {
        NumbersClient client = new GRPCNumbersClientImpl(SERVER_PORT, SERVER_HOST);
        client.run();
    }
}
