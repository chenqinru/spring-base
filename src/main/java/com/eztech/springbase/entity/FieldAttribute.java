package com.eztech.springbase.entity;


import com.eztech.springbase.enums.FieldTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据库字段属性
 *
 * @author chenqinru
 * @date 2023/08/30
 */
@Data
@AllArgsConstructor
public class FieldAttribute {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型
     */
    private FieldTypeEnums type;

    /**
     * 注释
     */
    private String comment;
}
