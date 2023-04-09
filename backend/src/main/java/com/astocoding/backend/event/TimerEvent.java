package com.astocoding.backend.event;

import lombok.Data;

@Data
public class TimerEvent implements Event{

    private long askCurrentTime;

    public static TimerEvent of(long currentTimeStamp){
        TimerEvent timerEvent = new TimerEvent();
        timerEvent.askCurrentTime = currentTimeStamp;
        return timerEvent;
    }
}
