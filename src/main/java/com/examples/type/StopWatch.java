package com.examples.type;

public interface StopWatch {

    void start();

    void stop();

    void resume();

    void lap();

    void reset();

    Boolean getRunning();

    Boolean getStopped();

    /**
     * Returns No. of seconds elapsed
     *
     * @return
     */
    Long getTotalDuration();

    /**
     * Print summary
     * @return
     */

    String prettyPrint();
}
