package com.lee.lecture.system.model.query;


import lombok.Data;

@Data
public class LecturePageQuery {
    
    private String title;
    
    private Integer status;
    
    private String[] timeRange;
} 