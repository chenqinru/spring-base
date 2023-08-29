package com.eztech.springbase.utils.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;


/**
 * 树结构实体类
 *
 * @author chenqinru
 * @date 2023/07/29
 */
public class TreeSelect implements Serializable {
    /**
     * 父节点ID
     */
    @JsonIgnore
    private Long parentId;

    /**
     * 节点ID
     */
    private Long value;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {
    }

    public TreeSelect(Long value, String label) {
        this.value = value;
        this.label = label;
    }

    public TreeSelect(Long parentId, Long value, String label) {
        this.parentId = parentId;
        this.value = value;
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelect> children) {
        this.children = children;
    }

}
