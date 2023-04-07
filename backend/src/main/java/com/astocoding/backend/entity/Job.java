package com.astocoding.backend.entity;

import com.astocoding.backend.enums.JobCategory;
import lombok.Data;

@Data
public class Job {
    private String jobName;
    private String jobDescription;
    private JobCategory jobCategory;

//    TimingJobFields
    private String cronExpression;

}
