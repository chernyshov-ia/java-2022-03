package ru.otus.client;

import io.grpc.ManagedChannelBuilder;
import org.slf4j.LoggerFactory;
import ru.otus.generated.RemoteDBServiceGrpc;
import ru.otus.generated.RequestMessage;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

public class GRPCNumbersClientImpl implements NumbersClient {
    private static final Logger logger = LoggerFactory.getLogger(NumbersClient.class);

    private final int port;
    private final String host;

    public GRPCNumbersClientImpl(int port, String host) {
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() throws Exception {
        logger.info("numbers Client is starting...");

        var channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        var stub = RemoteDBServiceGrpc.newStub(channel);

        var latchComplete = new CountDownLatch(1);

        var request = RequestMessage.newBuilder()
                .setFirstValue(1)
                .setLastValue(10)
                .build();

        var clientStreamObserver = new ClientStreamObserver(() -> latchComplete.countDown());

        stub.requestNumbers(request, clientStreamObserver);

        long currentValue = 0;

        while (currentValue < 60) {
            currentValue = currentValue + clientStreamObserver.getLastValueAndReset() + 1;

            logger.info("currentValue: {}", currentValue);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

        }


        latchComplete.await();

        channel.shutdown();
    }
}
