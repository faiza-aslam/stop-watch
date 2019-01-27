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

public class SimpleStopWatch implements StopWatch {

    private Instant startTime, endTime;
    private Duration duration;
    private List<LapDto> lapsList=new ArrayList<>();

    private Boolean isRunning=false;
    private Boolean isStopped=false;
    private Duration currentLapDuration;

    @Override
    public Boolean getRunning() {
        return isRunning;
    }

    @Override
    public Boolean getStopped() {
        return isStopped;
    }

    @Override
    public Long getTotalDuration() {
        return duration==null? 0L :duration.get(ChronoUnit.SECONDS);
    }

    public void setDuration(Duration duration) {
        if(this.duration == null) {
            this.duration = duration;
        } else {
            this.duration = this.duration.plus(duration);
        }
    }

    private void setCurrentLapDuration(Duration duration) {
        if(this.currentLapDuration == null) {
            this.currentLapDuration = duration;
        } else {
            this.currentLapDuration = this.currentLapDuration.plus(duration);
        }
    }

    @Override
    public void start() {
        if(isRunning) {
            throw new IllegalStateException("SimpleStopWatch already started");
        }
        System.out.println("Starting SimpleStopWatch - "+new Date());
        isRunning = true;
        startTime = Instant.now();
    }

    @Override
    public void stop() {
        if(!isRunning) {
            throw new IllegalStateException("SimpleStopWatch is not running, cannot be stopped");
        }
        System.out.println("Stopping SimpleStopWatch - "+new Date());
        isStopped = true;
        isRunning = false;
        endTime = Instant.now();
        Duration diff = Duration.between(startTime, endTime);
        this.setCurrentLapDuration(diff);
    }

    @Override
    public void resume() {
        if(!isStopped) {
            throw new IllegalStateException("SimpleStopWatch is not stopped, cannot be resumed");
        }
        System.out.println("Resuming SimpleStopWatch - " + new Date());
        isRunning = true;
        isStopped = false;
        startTime = Instant.now();
    }

    @Override
    public void lap() {
        if(!isRunning) {
            throw new IllegalStateException("SimpleStopWatch is not running");
        }

        Integer lapNo = lapsList.size()+1;
        endTime = Instant.now();
        this.setCurrentLapDuration(Duration.between(startTime, endTime));
        System.out.println("Lapping SimpleStopWatch "+lapNo+" - " + new Date());
        LapDto lap = new LapDto(lapNo, this.currentLapDuration);
        System.out.println(lap.toString());
        lapsList.add(lap);

        this.setDuration(currentLapDuration);
        this.currentLapDuration = null;
        this.startTime = Instant.now();
    }

    @Override
    public void reset() {
        if(!isStopped) {
            throw new IllegalStateException("SimpleStopWatch is not stopped - cannot be reset");
        }
        System.out.println("Resetting SimpleStopWatch - "+new Date());
        lapsList = new ArrayList<>();
        isRunning = false;
        isStopped = false;
        duration = null;
        currentLapDuration = null;
    }

    public String shortSummary() {
        return "########################################## \n StopWatch running time (seconds) = " + this.getTotalDuration();
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(this.shortSummary());
        sb.append('\n');
        if(this.lapsList.size() > 0) {
            sb.append("-----------------------------------------\n");
            sb.append("s       %       Lap No.\n");
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
