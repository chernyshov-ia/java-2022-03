package ru.otus.server;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.client.NumbersClient;
import ru.otus.generated.RemoteDBServiceGrpc;
import ru.otus.generated.RequestMessage;
import ru.otus.generated.ResponseMessage;

import java.util.concurrent.TimeUnit;

public class RemoteDBServiceImpl extends RemoteDBServiceGrpc.RemoteDBServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(RemoteDBServiceImpl.class);

    @Override
    public void requestNumbers(RequestMessage request, StreamObserver<ResponseMessage> responseObserver) {
        for (long i = request.getFirstValue() + 1; i <= request.getLastValue(); i++) {
            try {
                var response = ResponseMessage.newBuilder().setValue(i).build();
                responseObserver.onNext(response);
                logger.info("new number: {}", i);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}
