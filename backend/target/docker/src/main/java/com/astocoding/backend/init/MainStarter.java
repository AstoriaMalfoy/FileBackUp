package com.astocoding.backend.init;

import com.astocoding.backend.anno.JobCollectionInstance;
import com.astocoding.backend.enums.JobCategory;
import com.astocoding.backend.exception.NotFoundJobCollection;
import com.astocoding.backend.job.JobCollection;
import com.astocoding.backend.timer.Timer;
import com.astocoding.backend.utils.ApplicationContextProvider;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MainStarter implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private Timer timer;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext().getParent() == null) {

            log.debug("spring application context init finish");
            log.info("the application init start ....");

            log.debug("init job collection");
            List<JobCollection> jobCollections = scanJobCollection();
            if(CollectionUtils.isEmpty(jobCollections)){
                log.error("not found job collection");
                throw new NotFoundJobCollection("not found job collection");
            }
            Map<JobCategory, JobCollection> jobCategoryDict = new HashMap<>();

            for (JobCollection jobCollection : jobCollections) {
                try {
                    jobCollection.init();
                    JobCollectionInstance annotation = jobCollection.getClass().getAnnotation(JobCollectionInstance.class);
                    if (annotation == null) {
                        log.error("job collection :{}  not found JobCollectionInstance annotation", jobCollection.getClass().getName());
                        continue;
                    }
                    jobCategoryDict.put(annotation.jobCategory(), jobCollection);
                } catch (Exception e) {
                    log.error("init job collection error", e);
                }
            }
            log.info("init job collection success");


            log.info("timer init start ... ");
            timer.setInterval(1, TimeUnit.SECONDS).setJobCollection(jobCategoryDict.get(JobCategory.TIMING));
            timer.start();
        }


    }

    //    扫描所有JobCollection 并初始化
    public List<JobCollection> scanJobCollection() {
        Map<String, Object> beansWithAnnotation = ApplicationContextProvider.getApplicationContext().getBeansWithAnnotation(JobCollectionInstance.class);
        log.info("get Job Collection :{}", beansWithAnnotation);
        if (!CollectionUtils.isEmpty(beansWithAnnotation)) {
            Collection<Object> values = beansWithAnnotation.values();
            List<JobCollection> jobCollections = new ArrayList<>();
            for (Object value : values) {
                if (value instanceof JobCollection) {
                    jobCollections.add((JobCollection) value);
                }
            }
            return jobCollections;
        } else {
            log.error("not found JobCollection");
            return Collections.emptyList();
        }
    }


}
