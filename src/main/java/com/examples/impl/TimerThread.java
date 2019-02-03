package com.examples.impl;

public class TimerThread implements Runnable {

    private /*volatile*/ boolean stopTimer = false;
    private int hours, minutes, seconds = 0;
    private int totalSeconds = 0;

    @Override
    public void run() {
        try {
            while (!stopTimer) {
                count();
                String time = formatTime();
                System.out.print("\b\b\b\b\b\b\b\b");
                System.out.print(time);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupting thread: "+Thread.currentThread().getName());
//            this.stopTimer = true;
            e.printStackTrace();
        }
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    private String formatTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void count() {
        seconds++;
        totalSeconds++;

        if(seconds >= 60) {
            minutes++;
            seconds=0;
        }
        if(minutes >= 60) {
            hours++;
            minutes=0;
            seconds=0;
        }
        if (hours >= 24) {
            seconds=0;
            minutes=0;
            hours=0;
        }

    }

    public void stop() {
        this.stopTimer = true;
//        System.out.println("Stopping Timer: "+this.stopTimer);
    }

    public void reset() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        totalSeconds = 0;
//        System.out.println("Resetting Timer: " + this.hours + ":"+this.minutes+":"+this.seconds);
    }

    /*private static String formatMilliSecondsToTime(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }*/



    /*public static void main(String[] args) throws InterruptedException {
        System.out.print("20:45:08");
        for(int i=0; i<10; i++) {
            String time = formatMilliSecondsToTime(System.currentTimeMillis());
            System.out.print("\b\b\b\b\b\b\b\b");
            System.out.print(time);
            Thread.sleep(1000);
        }
    }*/
}
