package ru.otus;

import ru.otus.server.GRPCServerImpl;

public class MyGRPCServer {
    public static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws Exception {
        var server = new GRPCServerImpl(SERVER_PORT);
        server.start();
    }
}
