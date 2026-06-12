package com.pluralsight.concerttracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 ->
                    case 2 ->
                    case 3 ->
                    case 4 ->
                    case 5 ->
                    case 6 ->
                    case 0 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default:
                        System.out.println("Invalid option. Please choose 0 through 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("Main Menu");
        System.out.println("1) Concerts");
        System.out.println("2) Search concerts");
        System.out.println("3) Artists");
        System.out.println("4) Venues");
        System.out.println("5) Promoters");
        System.out.println("6) Reports");
        System.out.println("0) Quit");
    }

}
