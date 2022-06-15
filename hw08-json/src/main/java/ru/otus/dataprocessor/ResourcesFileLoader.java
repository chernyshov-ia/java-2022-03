package ru.otus.dataprocessor;

import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    final private String filename;
    final private Gson gson;

    public ResourcesFileLoader(String fileName) {
        this.filename = fileName;
        this.gson = new Gson();
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        try (var reader = Files.newBufferedReader(Path.of(filename))) {
            Measurement[] arr = gson.fromJson(reader, Measurement[].class);
            return Arrays.asList(arr);
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }
}
