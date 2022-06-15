package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;


import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

public class FileSerializer implements Serializer {
    final private String filename;
    final private JsonMapper mapper;

    public FileSerializer(String fileName) {
        this.filename = fileName;
        this.mapper = JsonMapper.builder().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).build();
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        try (var writer = Files.newBufferedWriter(Path.of(filename),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            mapper.writeValue(writer, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
