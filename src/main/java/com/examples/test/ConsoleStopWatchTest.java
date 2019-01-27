package com.examples.test;

import com.examples.impl.SimpleStopWatch;
import com.examples.type.StopWatch;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleStopWatchTest {

    public static void main(String[] args) {

        System.out.println(" *** StopWatch *** \n Press 's' to Start");
        Scanner scanner = new Scanner(System.in);

        try {

            String input = scanner.nextLine();
            if ("s".equalsIgnoreCase(input)) {
                StopWatch sw = new SimpleStopWatch();
                registerHook(sw);
                sw.start();
                System.out.println("Press 'l' to lap, 'r' to resume, 's' to stop, 'e' to reset ... \n");
                do {
                    input = scanner.nextLine();
                    switch (input.toLowerCase()) {
                        case "l":
                            sw.lap();
                            break;

                        case "s":
                            sw.stop();
                            break;

                        case "r":
                            sw.resume();
                            break;

                        case "e":
                        /*System.out.println(sw.prettyPrint());
                        sw.reset();*/
                            break;

                        default:
                            System.out.println("Invalid input. Please type again...");
                            break;
                    }

                } while (!"e".equalsIgnoreCase(input));

            } else {
                System.out.println("Bye bye ....");
            }
        } catch (NoSuchElementException ex) {

        } finally {
            scanner.close();
        }
    }

    private static void registerHook(StopWatch sw) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook is running !");
            if (sw != null) {
                if(!sw.getStopped()) {
                    sw.stop();
                }
                System.out.println(sw.prettyPrint());
                sw.reset();
            }
        }));
    }
}
