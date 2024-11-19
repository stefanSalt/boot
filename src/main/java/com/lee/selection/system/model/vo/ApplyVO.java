package com.lee.selection.system.model.vo;

import com.lee.selection.system.model.entity.Apply;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 */
@Getter
@Setter
public class ApplyVO extends Apply {
    private String jobName;
    private String studentName;
    private String studentPhone;
    private String recruiterName;
}