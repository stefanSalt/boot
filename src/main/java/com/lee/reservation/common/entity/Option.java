package com.lee.reservation.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 下拉选项对象
 *
 * @Date 2024-09-23 18:02
 */
@Data
public class Option<T> implements java.io.Serializable{

    public Option(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public Option(T value, String label, List<Option<T>> children) {
        this.value = value;
        this.label = label;
        this.children= children;
    }

    /**
     * 选项的值
     */
    private T value;

    /**
     * 选项标签
     */
    private String label;

    /**
     *子选项列表
     */
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<Option<T>> children;

}