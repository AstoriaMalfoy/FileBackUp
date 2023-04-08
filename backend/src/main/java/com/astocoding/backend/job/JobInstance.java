package com.astocoding.backend.job;

import com.astocoding.backend.job.config.JobConfig;

import java.util.List;

public interface JobInstance {
    List<String> getFilePaths();
}
