package com.examples.dto;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class LapDto {

    private Integer lapNo;
    private Instant startTime;
    private Instant endTime;
    private Duration duration;

    public LapDto(Integer lapNo, Instant startTime, Instant endTime) {
        this.lapNo = lapNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }

    public LapDto(Integer lapNo, Duration duration) {
        this.lapNo = lapNo;
        this.duration = duration;
    }

    public Integer getLapNo() {
        return lapNo;
    }

    public Duration getDuration() {
        return duration;
    }

    public Long getDurationInSeconds() {
        return duration.get(ChronoUnit.SECONDS);
    }

    @Override
    public String toString() {
        return "LapDto{" +
                "lapNo=" + lapNo +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + getDurationInSeconds() + "s" +
                '}';
    }

}
