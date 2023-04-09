package com.astocoding.backend.job;

import com.astocoding.backend.anno.JobCollectionInstance;
import com.astocoding.backend.enums.JobCategory;
import com.astocoding.backend.event.Event;
import com.astocoding.backend.event.TimerEvent;
import com.astocoding.backend.exception.JobRepeatException;
import com.astocoding.backend.job.config.JobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@JobCollectionInstance(jobCategory = JobCategory.TIMING)
public class TimerJobCollection implements JobCollection {


    private List<JobConfig> jobConfigList;

    private Map<String, JobInstance> jobInstanceDict;

    @Override
    public void init() {
        this.jobConfigList = new ArrayList<>();
        this.jobInstanceDict = new HashMap<>();
//        todo 需要从文件中读取jobConfigList
        log.info("init timer job collection");
    }

    @Override
    public boolean addJob(JobConfig jobConfig) {
        if (jobInstanceDict.containsKey(jobConfig.getJobName())) {
            log.error("job name :{}  is exist ", jobConfig.getJobName());
            throw new JobRepeatException("job name :" + jobConfig.getJobName() + "  is exist ");
        }
        this.jobConfigList.add(jobConfig);
        this.jobInstanceDict.put(jobConfig.getJobName(), TimerJobInstance.of(jobConfig));
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
    public void spreadEvent(Event event) {
        log.info("timer job collection receive event :{}", event);
        // todo 事件分发
        TimerEvent timerEvent = (TimerEvent) event;
        log.info("find the job run on time :{}", timerEvent.getAskCurrentTime());
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
