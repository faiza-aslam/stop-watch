package com.examples.test;

import com.examples.impl.SimpleStopWatch;
import com.examples.type.StopWatch;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleStopWatchTest {

    public static void main(String[] args) {

        System.out.println(" *** StopWatch *** ");
        StopWatch sw = new SimpleStopWatch();
        registerHook(sw);

        sw.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Stop Watch started -----");

        try {
            String input;
            System.out.println("Press 'l' to lap, 's' to stop ... \n");
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
                        System.out.println("Invalid input. Please type again...");
                        break;
                }

            } while (!"s".equalsIgnoreCase(input));


        } catch (NoSuchElementException ex) {

        } finally {
            scanner.close();
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
