package ru.otus.jdbc.mapper;

import ru.otus.core.annotations.Id;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData {

    private final Class<T> metaClass;
    private String name;
    private Constructor<T> constructor;
    private Field idField;
    private List<Field> allFields;
    private List<Field> fieldsWithoutId;


    public EntityClassMetaDataImpl(Class<T> clazz) {
        metaClass = clazz;
        loadClassMetadata();
    }

    private void loadClassMetadata() {

        allFields = Arrays.stream(metaClass.getDeclaredFields()).toList();

        allFields.forEach((f) -> f.setAccessible(true));

        idField = allFields.stream()
                .filter((f) -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();


        name = metaClass.getSimpleName().toLowerCase();

        fieldsWithoutId = new ArrayList<>(allFields);
        fieldsWithoutId.remove(idField);

        try {
            constructor = metaClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldsWithoutId;
    }

}
