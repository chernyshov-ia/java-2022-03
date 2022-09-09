package ru.petrelevich.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.petrelevich.domain.Message;

import java.util.List;

public interface DataStore {

    Mono<Message> saveMessage(Message message);

    Flux<Message> loadMessages(String roomId);

    Flux<Message> loadMessagesAll();
}
