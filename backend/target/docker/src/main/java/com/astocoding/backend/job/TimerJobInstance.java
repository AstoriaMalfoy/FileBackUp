package com.astocoding.backend.job;

import com.astocoding.backend.fileStore.fileSource.FileSource;
import com.astocoding.backend.fileStore.fileTarget.FileTarget;
import com.astocoding.backend.job.config.JobConfig;

import java.lang.instrument.ClassFileTransformer;
import java.util.List;

public class TimerJobInstance implements JobInstance{

    private FileSource fileSource;
    private FileTarget fileTarget;


    public TimerJobInstance(JobConfig jobConfig){
        this.fileSource = jobConfig.fileSource();
        this.fileSource.checkAccess();

        this.fileTarget = jobConfig.fileTarget();
    }







    public static TimerJobInstance of(JobConfig jobConfig){
        return new TimerJobInstance(jobConfig);
    }

    @Override
    public List<String> getFilePaths() {
        return fileSource.getAllFilePaths();
    }

    @Override
    public void doJob() {
        
    }
}
