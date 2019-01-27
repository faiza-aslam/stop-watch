package com.examples.test;

import com.examples.impl.SimpleStopWatch;
import com.examples.type.StopWatch;

public class TestStopWatch {
    public static void main(String[] args) throws Exception {

        /*StopWatch sw = new SimpleStopWatch();
        sw.start();
        Thread.sleep(5000);
        sw.stop();
        Thread.sleep(1000);
        sw.resume();
        Thread.sleep(2000);
        sw.lap();
        Thread.sleep(3000);
        sw.lap();
        Thread.sleep(4000);
        sw.stop();
        Thread.sleep(2000);
        sw.reset();*/

        /*StopWatch sw1 = new SimpleStopWatch();
        sw1.start();
        Thread.sleep(1000);
        sw1.lap();
        Thread.sleep(2000);
        sw1.lap();
        Thread.sleep(2000);
        sw1.lap();
        Thread.sleep(1000);
        sw1.stop();
        Thread.sleep(2000);
        sw1.resume();
        sw1.stop();
        System.out.println(sw1.prettyPrint());
        sw1.reset();*/

        StopWatch sw1 = new SimpleStopWatch();
        sw1.start();
        Thread.sleep(1000);
        sw1.stop();
        System.out.println(sw1.prettyPrint());
        sw1.reset();
    }
}
