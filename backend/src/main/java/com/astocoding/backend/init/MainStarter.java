package com.astocoding.backend.init;

import com.astocoding.backend.timer.Timer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MainStarter implements ApplicationListener<ApplicationStartedEvent> {


    @Resource
    private Timer timer;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("application start success");
//        初始化定时器
        timer.setInterval(1, TimeUnit.SECONDS);
        timer.start();

    }
}
