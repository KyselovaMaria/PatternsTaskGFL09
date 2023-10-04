package patterns.example.movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieCatalog implements Serializable {
    private final List<Movie> movies;

    public MovieCatalog() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Movie> findMoviesByType(Movie.MovieType movieType) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getMovieType() == movieType) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> findMoviesByDirector(String director) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (director.equals(movie.getDirector())) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> searchMoviesByCategory(Movie.MovieType category) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getMovieType() == category) {
                result.add(movie);
            }
        }
        return result;
    }

    public Movie findMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder catalog = new StringBuilder();
        catalog.append("Movie Catalog:\n");
        for (Movie movie : movies) {
            catalog.append(movie.toString()).append("\n");
        }
        return catalog.toString();
    }
}
