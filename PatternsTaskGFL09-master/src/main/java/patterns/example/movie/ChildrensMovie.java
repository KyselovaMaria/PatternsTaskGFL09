package patterns.example.movie;

import java.io.Serializable;

public class ChildrensMovie extends Movie implements Serializable {

    public ChildrensMovie(String title) {
        super(title, MovieType.CHILDRENS);
    }

    public ChildrensMovie(String title, MovieType movieType) {
        super(title, movieType);
    }

    @Override
    public double calcRentalAmount(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
        return amount;
    }
}
