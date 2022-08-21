package ru.otus.client;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.generated.ResponseMessage;

public class ClientStreamObserver implements StreamObserver<ResponseMessage> {
    private static final Logger logger = LoggerFactory.getLogger(ClientStreamObserver.class);
    private final Runnable onComplete;

    private long lastValue;

    public ClientStreamObserver(Runnable onComplete) {
        this.onComplete = onComplete;
    }

    @Override
    public void onNext(ResponseMessage value) {
        setLastValue(value.getValue());
        logger.info("new value: {}", value.getValue());
    }

    @Override
    public void onError(Throwable t) {
        System.err.println(t);
    }

    @Override
    public void onCompleted() {
        logger.info("request completed");
        onComplete.run();
    }

    private synchronized void setLastValue(long value) {
        lastValue = value;
    }

    public synchronized long getLastValueAndReset() {
        var value = lastValue;
        lastValue = 0;
        return value;
    }
}
