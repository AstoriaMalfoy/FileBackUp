package com.astocoding.backend.timer;


import com.astocoding.backend.event.TimerEvent;
import com.astocoding.backend.job.JobCollection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Timer extends Thread{


//    时间间隔
    private int timerInterval;

//    timerInterval 的时间单位
    private TimeUnit timeUnit;

//    任务集合
    private JobCollection jobCollection;

    public Timer setInterval(long timeInterval, TimeUnit timeUnit){
        this.timerInterval = (int) timeInterval;
        this.timeUnit = timeUnit;
        return this;
    }

    public Timer setJobCollection(JobCollection jobCollection){
        this.jobCollection = jobCollection;
        return this;
    }




    @Override
    public void run() {
        log.info("timer is starting .... ");
        log.info("timer interval is {} , unit:{} ", timerInterval , timeUnit);
        TimerEvent timerEvent = new TimerEvent();
        while (!Thread.currentThread().isInterrupted()){
            try {
                timeUnit.sleep(timerInterval);
                if (Objects.isNull(jobCollection)){
                    log.error("job collection is null");
                    Thread.currentThread().interrupt();
                }
                timerEvent.setAskCurrentTime(System.currentTimeMillis());
                jobCollection.spreadEvent(timerEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
