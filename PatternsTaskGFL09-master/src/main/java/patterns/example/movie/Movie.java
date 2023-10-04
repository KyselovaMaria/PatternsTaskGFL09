package patterns.example.movie;
import java.io.Serializable;

public abstract class Movie implements Serializable {
    private final String title;
    private final MovieType movieType;
    private String countryOfOrigin;
    private String description;
    private String director;
    private String actors;

    public enum MovieType {
        REGULAR, NEW_RELEASE, CHILDRENS, DRAMA, COMEDY, THRILLER
    }

    public Movie(String title, MovieType movieType) {
        this.title = title;
        this.movieType = movieType;
        this.countryOfOrigin = null;
        this.description = null;
        this.director = null;
        this.actors = null;
    }

    public abstract double calcRentalAmount(int daysRented);

    public MovieType getMovieType() {
        return movieType;
    }

    public String getTitle() {
        return title;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", movieType=" + movieType +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                '}';
    }
}

