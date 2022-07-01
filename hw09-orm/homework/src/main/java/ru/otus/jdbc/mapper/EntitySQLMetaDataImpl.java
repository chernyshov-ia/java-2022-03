package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {
    private String selectAll;
    private String selectById;
    private String insertSql;
    private String updateSql;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaData) {

        var joinerAll = new StringJoiner(",");
        for (Field f : entityClassMetaData.getAllFields()) {
            joinerAll.add(f.getName());
        }

        selectAll = "select " + joinerAll + " from " + entityClassMetaData.getName();
        selectById = selectAll + " where " + entityClassMetaData.getIdField().getName() + " = ?";


        var joinerWithoudId = new StringJoiner(",");
        var joinerParams = new StringJoiner(",");
        var joinerUpdate = new StringJoiner(",");
        for (Field f : entityClassMetaData.getFieldsWithoutId()) {
            joinerWithoudId.add(f.getName());
            joinerParams.add("?");
            joinerUpdate.add(f.getName() + " = ?");
        }

        insertSql = "insert into " + entityClassMetaData.getName() + "(" +
                joinerWithoudId + ") values (" + joinerParams + ")";

        updateSql = "update " + entityClassMetaData.getName() + " set " + joinerUpdate +
                " where " + entityClassMetaData.getIdField().getName() + " = ?";

    }

    @Override
    public String getSelectAllSql() {
        return selectAll;
    }

    @Override
    public String getSelectByIdSql() {
        return selectById;
    }

    @Override
    public String getInsertSql() {
        return insertSql;
    }

    @Override
    public String getUpdateSql() {
        return updateSql;
    }
}
