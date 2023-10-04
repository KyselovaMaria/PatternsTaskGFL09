package patterns.example.movie;

import java.io.Serializable;

public class NewReleaseMovie extends Movie implements Serializable {

    public NewReleaseMovie(String title) {
        super(title, MovieType.NEW_RELEASE);
    }

    public NewReleaseMovie(String title, MovieType movieType) {
        super(title, movieType);
    }
    @Override
    public double calcRentalAmount(int daysRented) {
        return daysRented * 3;
    }
}