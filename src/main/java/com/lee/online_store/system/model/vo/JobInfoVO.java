package com.lee.online_store.system.model.vo;

import com.lee.online_store.system.model.entity.JobInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 */
@Getter
@Setter
public class JobInfoVO extends JobInfo {
    private String companyName;
    private String recruiterName;
    private String recruiterPosition;
}