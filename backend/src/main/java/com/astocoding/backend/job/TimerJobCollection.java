package com.astocoding.backend.job;

import com.astocoding.backend.exception.JobRepeatException;
import com.astocoding.backend.job.config.JobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TimerJobCollection implements JobCollection{


    private List<JobConfig> jobConfigList;

    private Map<String,JobInstance> jobInstanceDict;

    @Override
    public void init() {
        this.jobConfigList = new ArrayList<>();
        this.jobInstanceDict = new HashMap<>();
//        todo 需要从文件中读取jobConfigList
    }

    @Override
    public boolean addJob(JobConfig jobConfig) {
        if (jobInstanceDict.containsKey(jobConfig.getJobName())){
            log.error("job name :{}  is exist ",jobConfig.getJobName());
            throw new JobRepeatException("job name :"+jobConfig.getJobName()+"  is exist ");
        }
        this.jobConfigList.add(jobConfig);
        this.jobInstanceDict.put(jobConfig.getJobName(),TimerJobInstance.of(jobConfig));
        return true;
    }

    @Override
    public JobConfig removeJob() {
        return null;
    }

    @Override
    public boolean updateJob(JobConfig jobConfig) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public JobInstance next() {
        return null;
    }
}
