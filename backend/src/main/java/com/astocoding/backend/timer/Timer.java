package com.astocoding.backend.timer;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public void setInterval(long timeInterval, TimeUnit timeUnit){
        this.timerInterval = (int) timeInterval;
        this.timeUnit = timeUnit;
    }


    @Override
    public void run() {
        log.info("timer is starting .... ");
        log.info("timer interval is {} , unit:{} ", timerInterval , timeUnit);
        while (!Thread.currentThread().isInterrupted()){
            try {
                timeUnit.sleep(timerInterval);

                log.info("timer is running ....  ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
