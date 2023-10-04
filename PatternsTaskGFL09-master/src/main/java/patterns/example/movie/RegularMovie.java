package patterns.example.movie;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title, MovieType.REGULAR);
    }

    public RegularMovie(String title, MovieType movieType) {
        super(title, movieType);
    }
    @Override
    public double calcRentalAmount(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

}
