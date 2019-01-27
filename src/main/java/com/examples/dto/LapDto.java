package com.examples.dto;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LapDto {

    private Integer lapNo;
    private Duration duration;

    public LapDto(Integer lapNo, Duration duration) {
        this.lapNo = lapNo;
        this.duration = duration;
    }

    public Integer getLapNo() {
        return lapNo;
    }

    public void setLapNo(Integer lapNo) {
        this.lapNo = lapNo;
    }

    public Duration getDuration() {
        return duration;
    }

    public Long getDurationInSeconds() {
        return duration.get(ChronoUnit.SECONDS);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "LapDto{" +
                "lapNo=" + lapNo +
                ", duration=" + duration.get(ChronoUnit.SECONDS) +"s" +
                '}';
    }
}
