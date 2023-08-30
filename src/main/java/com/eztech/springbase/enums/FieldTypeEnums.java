package com.eztech.springbase.enums;

import java.util.Optional;

/**
 * 字段类型枚举
 *
 * @author chenqinru
 * @date 2023/08/30
 */
public enum FieldTypeEnums {
    VARCHAR("varchar", "String"),
    INT("int", "Integer"),
    BIGINT("bigint", "Long"),
    TINYINT("tinyint", "Integer"),
    DATETIME("datetime", "LocalDateTime"),
    DATE("date", "LocalDateTime"),
    TIMESTAMP("timestamp", "LocalDateTime"),
    TEXT("text", "String"),
    LONGTEXT("longtext", "String"),
    DECIMAL("decimal", "BigDecimal");

    private String dbType;
    private String javaType;

    FieldTypeEnums(String dbType, String javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    public static Optional<FieldTypeEnums> of(String dbType) {
        for (FieldTypeEnums fieldType : FieldTypeEnums.values()) {
            if (fieldType.getDbType().equals(dbType)) {
                return Optional.of(fieldType);
            }
        }
        return Optional.empty();
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
