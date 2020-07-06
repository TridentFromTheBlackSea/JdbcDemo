package be.infernalwhale;

import be.infernalwhale.data.NonUniqueResultException;
import be.infernalwhale.model.Beer;
import be.infernalwhale.services.BeerService;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int mainChoice;
        int subChoice = -1;

        do {
            showMenu();
            mainChoice = requestIntInput(0, 3);

            if (mainChoice != 0) {
                showSubMenu(mainChoice);
                subChoice = requestIntInput(0, 6);

                handleUserChoice(mainChoice, subChoice);
            }
        } while (mainChoice != 0 && subChoice != 0);
    }

    private static void handleUserChoice(int mainChoice, int subChoice) {
        if (mainChoice == 1) {
            BeerService beerService = new BeerService();
            if (subChoice == 1) {
                // show all beers
                List<Beer> beers = null;

                try {
                    beers = beerService.getAllBeers();
                    beers.forEach(b -> System.out.println(b.getSingleLine()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                }

            }

            if (subChoice == 2) {
                // filter beers by alcohol
                System.out.print("Please enter lower bound: ");
                int lower = requestIntInput(0, 15);
                System.out.print("Please enter upper bound: ");
                int upper = requestIntInput(lower, 16);
                try {
                    List<Beer> beers = beerService.getBeersByAlcohol(lower, upper);
                    beers.forEach(b -> System.out.println(b.getSingleLine()));
                } catch (SQLException throwables) {
                    System.out.println("Problems with db...");
                }
            }

            if (subChoice == 3) {
                System.out.println("Please enter id of beer: ");
                int id = requestIntInput(1, Integer.MAX_VALUE);

                Optional<Beer> optionalBeer = null;
                try {
                    optionalBeer = beerService.getBeerById(id);
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                } catch (NonUniqueResultException e) {
                    System.out.println(e.getMessage());
                }
                if (optionalBeer.isPresent()) {
                    System.out.println(optionalBeer.get().toString());
                } else {
                    System.out.println("Beer with id: " + id + " was not found in db.");
                }
            }
        }
    }

    private static void showMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Beers");
        System.out.println("2. Brewers");
        System.out.println("3. Categories");
    }

    private static void showSubMenu(int choice) {
        if (choice == 1) {
            System.out.println("0. Exit");
            System.out.println("1. Show all beers");
            System.out.println("2. Show all beers filtered by Alcohol");
            System.out.println("3. Show beer by id");
            System.out.println("4. Create a new beer");
            System.out.println("5. Edit a beer");
            System.out.println("6. Delete a beer (by id)");
        }

        if (choice == 2) {
            System.out.println("This shit doesn't work yet...");
        }
    }

    private static int requestIntInput(int lower, int upper) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        do {
            try {
                System.out.print("Make a choice: ");
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                input = -1;
            }
            scanner.nextLine();
            if (input < lower || input > upper) System.out.println("That's not a valid number, bruh");
        } while (input < lower || input > upper);

        return input;
    }

    private static void printResult(List<String> result) {
        result.forEach(System.out::println);
    }

}
