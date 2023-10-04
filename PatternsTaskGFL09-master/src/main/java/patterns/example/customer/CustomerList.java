package patterns.example.customer;

import patterns.example.movie.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerList implements Serializable {
    private final List<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public void saveCustomersToTextFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Customer customer : customers) {
                writer.write("Name: " + customer.getName());
                writer.newLine();
                writer.write("Rentals: " + customer.getRentals());
                writer.newLine();
                writer.write("Statement: " + customer.statement());
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomersFromTextFile(String filename, MovieCatalog movieCatalog) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<Customer> loadedCustomers = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    String name = line.substring("Name: ".length());

                    List<Rental> rentals = new ArrayList<>();
                    String rentalsLine = reader.readLine();
                    if (rentalsLine != null && rentalsLine.startsWith("Rentals: ")) {
                        String[] rentalInfo = rentalsLine.substring("Rentals: ".length()).split(",");
                        for (String rental : rentalInfo) {
                            String[] rentalDetails = rental.split(":");
                            if (rentalDetails.length == 2) {
                                String movieTitle = rentalDetails[0].trim();
                                int daysRented = Integer.parseInt(rentalDetails[1].trim());

                                // Use the findMovieByTitle method to get the Movie object
                                Movie movie = movieCatalog.findMovieByTitle(movieTitle);
                                if (movie != null) {
                                    rentals.add(new Rental(movie, daysRented));
                                }
                            }
                        }
                    }

                    // Create Customer object and add it to loadedCustomers list
                    loadedCustomers.add(new Customer(name, rentals));
                }
            }

            // Replace the existing customers list with the loadedCustomers list
            customers.clear();
            customers.addAll(loadedCustomers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


