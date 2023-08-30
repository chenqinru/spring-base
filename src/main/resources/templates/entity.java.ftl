package ${basePackage}.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "${tableName}")
@Data
public class ${className} extends BaseEntity {

<#list fieldAttributes as fieldAttribute>
    /**
    * ${fieldAttribute.comment}
    */
    @Column
    private ${fieldAttribute.type.javaType} ${fieldAttribute.name};

</#list>
}
