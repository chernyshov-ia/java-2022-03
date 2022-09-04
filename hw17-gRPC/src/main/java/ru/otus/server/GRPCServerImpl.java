package ru.otus.server;

import io.grpc.ServerBuilder;

public class GRPCServerImpl implements NumbersServer {
    final private int serverPort;

    public GRPCServerImpl( int serverPort ) {
        this.serverPort = serverPort;
    }

    @Override
    public void start() throws Exception {
        var remoteDBService = new RemoteDBServiceImpl();

        var server = ServerBuilder
                .forPort(serverPort)
                .addService(remoteDBService).build();

        server.start();
        System.out.println("server waiting for client connections...");
        server.awaitTermination();
    }
}
