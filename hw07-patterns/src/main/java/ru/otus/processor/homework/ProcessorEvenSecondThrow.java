package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorEvenSecondThrow implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorEvenSecondThrow(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        var second = dateTimeProvider.getDate().getSecond();
        System.out.println("Current second = " + second);
        if (second % 2 == 0) {
            throw new RuntimeException("Even second exception");
        }
        return message;
    }
}
