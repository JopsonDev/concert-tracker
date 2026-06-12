package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
import com.pluralsight.concerttracker.services.ConcertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {
    private final ConcertService concertService;
    private final DataSourceTransactionManager dataSourceTransactionManager;

    public StartupRunner(ConcertService concertService, DataSourceTransactionManager dataSourceTransactionManager) {
        this.concertService = concertService;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

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
                    case 1 -> concertsMenuAction(scanner);
                    case 2 ->
                    case 3 -> searchVenueAction(scanner);
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

    public void concertsMenuAction(Scanner scanner){
        boolean running = true;
        while(running){
            concertsMenu();
            System.out.print("Input: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> concertService.allConcerts();
                    case 2 -> System.out.println(findConcertsByID(scanner));
                    case 3 -> addConcert(scanner);
                    case 4 -> updateConcertPrice(scanner);
                    case 5 -> updateConcertTicketsSold(scanner);
                    case 6 -> deleteConcert(scanner);
                    case 0 -> {
                        running = false;
                        System.out.println("Returning");
                    }
                    default -> System.out.println("Invalid option. Please choose 0 through 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            System.out.println();
        }
    }

    public Concert findConcertsByID(Scanner scanner){
        System.out.print("Input concert ID: ");
        long input = scanner.nextLong();
        Concert concert = concertService.findConcertById(input);
        scanner.nextLine();
        return concert;
    }

    public void updateConcertPrice(Scanner scanner){
        System.out.println("Please enter the concert ID of the concert you wish to update");
        Concert concert = findConcertsByID(scanner);
        System.out.print("New price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if(concertRules(price,concert.getTicketsSold(),concert.getArtistId(),concert.getVenueId(),concert.getPromoterId())){
            concert.setTicketPrice(price);
            concertService.saveConcert(concert);
        }
    }

    public void updateConcertTicketsSold(Scanner scanner){
        System.out.println("Please enter the concert ID of the concert you wish to update");
        Concert concert = findConcertsByID(scanner);
        System.out.print("Tickets sold: ");
        int sold = scanner.nextInt();
        scanner.nextLine();
        if(concertRules(concert.getTicketPrice(),sold,concert.getArtistId(),concert.getVenueId(),concert.getPromoterId())){
            concert.setTicketsSold(sold);
            concertService.saveConcert(concert);
        }
    }

    public void deleteConcert(Scanner scanner){
        System.out.println("Please enter the concert ID of the concert you wish to delete");
        Concert concert = findConcertsByID(scanner);
        System.out.println(concert.getId() + "Please enter DELETE as seen to confirm");
        String input = scanner.nextLine();
        if(input.equals("DELETE")){
            concertService.deleteConcert(concert);
            System.out.println("Deleted");
        } else {
            System.out.println("Returning");
        }
    }

    public void addConcert(Scanner scanner){
        System.out.print("Year of concert: ");
        int year = scanner.nextInt();
        System.out.print("Price of tickets: ");
        double price = scanner.nextDouble();
        System.out.print("Amount of tickets sold: ");
        int ticketSold = scanner.nextInt();
        System.out.print("Artist ID: ");
        long artistID = scanner.nextLong();
        System.out.println("Venue ID: ");
        long venueID = scanner.nextLong();
        System.out.println("Promoter ID: ");
        long promoterID = scanner.nextLong();
        scanner.nextLine();

        if(concertRules(price, ticketSold, artistID, venueID, promoterID)){
            concertService.saveConcert(new Concert(year, price, ticketSold, artistID, venueID, promoterID));
            System.out.println("Concert Added!");
        }
    }

    public boolean concertRules(double price, int ticketSold, long artistID, long venueID, long promoterID){
        Artist artist = concertService.findArtistById(artistID);
        Venue venue = concertService.findVenueById(venueID);
        Promoter promoter = concertService.findPromoterById(promoterID);

        if(artist == null || venue == null || promoter == null){
            System.out.println("Incorrect IDs");
            return false;
        }

        if(ticketSold < 0 || price < 0){
            System.out.println("Invalid price or tickets sold");
            return false;
        }

        if(venue.getCapacity() < ticketSold){
            System.out.println("Over Capacity");
            return false;
        }

        return true;
    }

    public void searchMenuAction(Scanner scanner) {
        boolean running = true;
        while (running) {
            searchMenu();
            System.out.print("Input: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> searchByYear(scanner);
                    case 2 -> searchByArtist(scanner);
                    case 3 -> searchByVenue(scanner);
                    case 4 ->
                    case 5 ->
                    case 6 -> System.out.println();
                    case 7 ->
                    case 0 -> {
                        running = false;
                        System.out.println("Returning");
                    }
                    default -> System.out.println("Invalid option. Please choose 0 through 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            System.out.println();
        }
    }

    public void searchByYear(Scanner scanner){
        System.out.println("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        printList(concertService.findConcertByYear(year));
    }

    public void searchByArtist(Scanner scanner){
        System.out.println("Artist ID: ");
        long artist = scanner.nextLong();
        scanner.nextLine();
        printList(concertService.findConcertByArtist(artist));
    }

    public void searchByVenue(Scanner scanner){
        System.out.println("Venue ID: ");
        long venue = scanner.nextLong();
        scanner.nextLine();
        printList(concertService.findConcertByVenue(venue));
    }

    public void searchVenueAction(Scanner scanner) {
        boolean running = true;
        while (running) {
            searchMenu();
            System.out.print("Input: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> addVenue(scanner);
                    case 2 -> updateVenueCapacity(scanner);
                    case 3 -> deleteVenue(scanner);
                    case 4 -> printList(findVenueByCity(scanner));
                    case 5 -> printList(findVenueByName(scanner));
                    case 6 -> printList(minCapacitySearch(scanner));
                    case 0 -> {
                        running = false;
                        System.out.println("Returning");
                    }
                    default -> System.out.println("Invalid option. Please choose 0 through 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            System.out.println();
        }
    }

    public void addVenue(Scanner scanner){
        System.out.print("Venue name: ");
        String name = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        concertService.saveVenue(new Venue(name, city, capacity));
    }

    public void updateVenueCapacity(Scanner scanner) {
        System.out.println("Please enter the venue ID of the venue you wish to update");
        Venue venue = concertService.findVenueById(scanner.nextLong());
        System.out.print("Updated capacity: ");
        int c = scanner.nextInt();
        scanner.nextLine();

        if (c > 0) {
            venue.setCapacity(c);
            concertService.saveVenue(venue);
            System.out.println("Capacity updated");
        } else {
            System.out.println("Capacity cant be less than 0");
        }
    }

    public <T> void printList(List<T> List){
        for (T t : List){
            System.out.println(t);
        }
    }

    public void deleteVenue(Scanner scanner){
        System.out.println("Please enter the venue ID of the venue you wish to delete");
        Venue venue = concertService.findVenueById(scanner.nextLong());
        scanner.nextLine();
        System.out.println(venue.getId() + "Please enter DELETE as seen to confirm");
        String input = scanner.nextLine();
        if(input.equals("DELETE")){
            concertService.deleteVenue(venue);
            System.out.println("Deleted");
        } else {
            System.out.println("Returning");
        }
    }

    public List<Venue> findVenueByCity(Scanner scanner){
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        return concertService.findVenueByCity(city);
    }

    public List<Venue> findVenueByName(Scanner scanner){
        System.out.print("Enter venue name: ");
        String name = scanner.nextLine();
        return concertService.findVenueByName(name);
    }

    public List<Venue> minCapacitySearch(Scanner scanner){
        System.out.println("Min Capacity: ");
        int minCap = scanner.nextInt();
        scanner.nextLine();
        return concertService.findVenueWithMoreThanCap(minCap);
    }

    public void venueMenu(){
        System.out.println("1). Add venue");
        System.out.println("2). Update capacity");
        System.out.println("3). Delete venue");
        System.out.println("4). Search by city");
        System.out.println("5). Search by name");
        System.out.println("6). Search by minimum capacity");
        System.out.println("0). Return");
    }

    public void searchMenu(){
        System.out.println("1). Search by year");
        System.out.println("2). Search by artist");
        System.out.println("3). Search by venue");
        System.out.println("4). Search by city");
        System.out.println("5). Search by maximum price");
        System.out.println("6). Search by price range");
        System.out.println("7). Advance search");
        System.out.println("0). Return");

    }

    public void concertsMenu(){
        System.out.println("1). List all concerts");
        System.out.println("2). Find by ID");
        System.out.println("3). Add a concert");
        System.out.println("4). Update concert price");
        System.out.println("5). Update tickets sold");
        System.out.println("6). Delete concert");
        System.out.println("0). Return");
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
