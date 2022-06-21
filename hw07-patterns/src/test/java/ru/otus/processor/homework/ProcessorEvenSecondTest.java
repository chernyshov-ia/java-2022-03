package ru.otus.processor.homework;

import org.junit.jupiter.api.*;
import ru.otus.model.Message;

import java.time.LocalDateTime;

public class ProcessorEvenSecondTest {

    private Message message;

    @BeforeEach
    private void prepare(){
      message = new Message.Builder(1L).field7("field7").build();
    }

    @Test
    @DisplayName("processor throw error")
    void throwTest() {
        var processor = new ProcessorEvenSecondThrow(() -> LocalDateTime.of(2022,1,1,22,10,22));
        Assertions.assertThrows(RuntimeException.class,() -> processor.process(message));
    }

    @Test
    @DisplayName("processor don't throw error")
    void okTest() {
        var processor = new ProcessorEvenSecondThrow(() -> LocalDateTime.of(2022,1,1,22,10,21));
        Assertions.assertDoesNotThrow(() -> processor.process(message));
    }
}
