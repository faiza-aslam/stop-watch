package com.examples.impl;

import com.examples.dto.LapDto;
import com.examples.type.StopWatch;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimerStopWatch implements StopWatch {

    private Instant startTime, endTime;
    private Duration duration; /* Duration of whole stop watch */
    private List<LapDto> lapsList=new ArrayList<>();

    private Boolean isRunning=false;
    private Boolean isStopped=true;
//    private Instant currentLapStartTime;
    private TimerThread tt = new TimerThread();
    private Thread timerThread = new Thread(tt);

    @Override
    public Boolean isRunning() {
        return isRunning;
    }

    @Override
    public Boolean isStopped() {
        return isStopped;
    }

    @Override
    public Long getTotalDuration() {
        return duration==null? 0L :duration.get(ChronoUnit.SECONDS);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public void start() {
        if(isRunning) {
            throw new IllegalStateException("TimerStopWatch already started");
        }
        //System.out.println("Starting TimerStopWatch - "+new Date());
        isRunning = true;
        startTime = Instant.now();
//        currentLapStartTime = Instant.now();
        timerThread.start();
    }

    @Override
    public void stop() {
        if(!isRunning) {
            throw new IllegalStateException("TimerStopWatch is not running, cannot be stopped");
        }
//        System.out.println("Stopping TimerStopWatch - "+new Date());
        isStopped = true;
        isRunning = false;
        endTime = Instant.now();
        tt.stop();
        Duration diff = Duration.between(startTime, endTime);
        this.setDuration(diff);
    }

    @Override
    public void lap() {
        if(!isRunning) {
            throw new IllegalStateException("TimerStopWatch is not running");
        }

        Integer lapNo = lapsList.size()+1;
//        System.out.println("Lapping SimpleStopWatch "+lapNo+" - " + new Date());
        LapDto lap = new LapDto(lapNo, Duration.ofMillis(tt.getTotalMilliSeconds()));
        tt.reset();
//        System.out.println(lap.toString());
        lapsList.add(lap);
//        this.currentLapStartTime = Instant.now();
    }

    @Override
    public void reset() {
        if(!isStopped) {
            throw new IllegalStateException("TimerStopWatch is not stopped - cannot be reset");
        }
//        System.out.println("Resetting TimerStopWatch - "+new Date());
        lapsList = new ArrayList<>();
        isRunning = null;
        isStopped = null;
        duration = null;
        startTime = null;
        endTime = null;
    }

    public String shortSummary() {
        return "########################################## \n StopWatch running time (seconds) = " + this.getTotalDuration();
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(this.shortSummary());
        sb.append('\n');
        if(this.lapsList.size() > 0) {
            sb.append("-----------------------------------------\n");
            sb.append("s       %        Lap No.\n");
            sb.append("-----------------------------------------\n");
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(5);
            nf.setGroupingUsed(false);
            NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(3);
            pf.setGroupingUsed(false);

            this.lapsList.forEach(l -> {
                sb.append(nf.format(l.getDurationInSeconds())).append("   ");
                sb.append(pf.format(new Double(l.getDurationInSeconds()) / new Double(this.getTotalDuration()))).append("     ");
                sb.append(l.getLapNo()).append("\n");
            });
        }

        return sb.toString();
    }

}
