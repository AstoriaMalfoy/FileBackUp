package com.astocoding.backend.job.config;

import com.astocoding.backend.enums.JobStatus;
import com.astocoding.backend.fileStore.fileSource.FileSource;
import com.astocoding.backend.fileStore.fileTarget.FileTarget;

import java.io.Serializable;

public interface JobConfig extends Serializable {
//    获取Job名称
    String getJobName();
//    Job 文件数据源
    FileSource fileSource();
//    Job 文件数据目标
    FileTarget fileTarget();
//    Job 状态
    JobStatus getJobStatus();

}
