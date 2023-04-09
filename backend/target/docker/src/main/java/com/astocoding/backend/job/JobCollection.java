package com.astocoding.backend.job;

import com.astocoding.backend.event.Event;
import com.astocoding.backend.job.config.JobConfig;

import java.util.Iterator;

// job 集合
public interface JobCollection extends Iterator<JobInstance> {

    void init();

    boolean addJob(JobConfig jobConfig);

    JobConfig removeJob();

    boolean updateJob(JobConfig jobConfig);


    boolean isEmpty();

    void spreadEvent(Event event);


}
