package com.examples.test;

import com.examples.impl.TimerStopWatch;
import com.examples.impl.TimerThread;
import com.examples.type.StopWatch;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleTimerStopWatchTest {

    public static void main(String[] args) {

        System.out.println(" *** StopWatch *** ");
        StopWatch sw = new TimerStopWatch();
        registerHook(sw);

        System.out.println("Enter 'a' to start stop watch");
        try (Scanner scanner = new Scanner(System.in)) {
            String startWatch = scanner.nextLine();
            while (!startWatch.equalsIgnoreCase("a")) {
                startWatch = scanner.nextLine();
            }

            sw.start();
            System.out.println("Stop Watch started -----");

            try {
                String input;
                System.out.println("Press 'l' to lap, 's' to stop ... ");
                do {
                    input = scanner.nextLine();
                    switch (input.toLowerCase()) {
                        case "l":
                            sw.lap();
                            break;

                        case "s":
                            sw.stop();
                            break;

                        default:
//                            System.out.println("Invalid input. Please type again...");
                            break;
                    }

                } while (!"s".equalsIgnoreCase(input));


            } catch (NoSuchElementException ex) {

            } finally {
                scanner.close();
            }
        } catch (NoSuchElementException ex) {

        }

    }

    private static void registerHook(StopWatch sw) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
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
