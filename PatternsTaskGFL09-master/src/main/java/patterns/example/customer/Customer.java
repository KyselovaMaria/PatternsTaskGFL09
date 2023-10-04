package patterns.example.customer;

import patterns.example.customer.Rental;
import patterns.example.movie.Movie;

import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
public
class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Rental each : rentals) {
            double thisAmount = each.getMovie().calcRentalAmount(each.getDaysRented());

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getMovieType() == Movie.MovieType.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
