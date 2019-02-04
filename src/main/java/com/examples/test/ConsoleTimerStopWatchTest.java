package com.examples.test;

import com.examples.impl.TimerStopWatch;
import com.examples.type.StopWatch;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleTimerStopWatchTest {

    public static void main(String[] args) {

        System.out.println(" *** StopWatch *** ");
        System.out.println("Press 'Enter' to lap, 'Ctrl+C' to stop ... ");
        StopWatch sw = new TimerStopWatch();
        registerHook(sw);

        sw.start();
//        System.out.println("Stop Watch started -----");

        try (Scanner scanner = new Scanner(System.in)) {
            String input;

            do {
                input = scanner.nextLine();
                switch (input.trim()) {
                    case "":
                        sw.lap();
                        break;

                    /*case "s":
                        sw.stop();
                        break;*/

                    default:
//                            System.out.println("Invalid input. Please type again...");
                        break;
                }

            } while (!"s".equalsIgnoreCase(input));

        } catch (NoSuchElementException ex) {
        }
    }

    private static void registerHook(StopWatch sw) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("Shutdown Hook is running !");
            if (sw != null) {
                if (!sw.isStopped()) {
                    sw.stop();
                }
                System.out.println(sw.prettyPrint());
                sw.reset();
            }
        }));
    }
}
