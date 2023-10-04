package patterns.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import patterns.example.customer.Customer;
import patterns.example.customer.CustomerList;
import patterns.example.customer.Rental;
import patterns.example.movie.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створюємо каталог фільмів
        MovieCatalog movieCatalog = new MovieCatalog();

        // Додаємо фільми до каталогу

        //movieCatalog.addMovie(new RegularMovie("Rambo", Movie.MovieType.REGULAR));
        //movieCatalog.addMovie(new RegularMovie("Lord of the Rings", Movie.MovieType.NEW_RELEASE));
        //movieCatalog.addMovie(new RegularMovie("Harry Potter", Movie.MovieType.CHILDRENS));
        RegularMovie rambo = new RegularMovie("Rambo");
        rambo.setCountryOfOrigin("USA");
        rambo.setDescription("Action movie");
        rambo.setDirector("John Doe");
        rambo.setActors("Actor 1, Actor 2");
        movieCatalog.addMovie(rambo);

        NewReleaseMovie lordOfTheRings = new NewReleaseMovie("Lord of the Rings");
        lordOfTheRings.setCountryOfOrigin("USA");
        lordOfTheRings.setDescription("Adventure movie");
        lordOfTheRings.setDirector("Jane Smith");
        lordOfTheRings.setActors("Actor A, Actor B");
        movieCatalog.addMovie(lordOfTheRings);

        ChildrensMovie harryPotter = new ChildrensMovie("Harry Potter");
        harryPotter.setCountryOfOrigin("USA");
        harryPotter.setDescription("Kids movie");
        harryPotter.setDirector("Mary Johnson");
        harryPotter.setActors("Actor X, Actress Y");
        movieCatalog.addMovie(harryPotter);


        //""" ПЕРЕГЛЯД ВСІХ ФІЛЬМІВ """

        try {
            // Створюємо шаблонізатор Velocity
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.init();

            // Створюємо VelocityContext та додаємо дані
            VelocityContext context = new VelocityContext();
            context.put("movies", movieCatalog.getAllMovies());

            // Отримуємо шаблон за допомогою Velocity
            Template template = velocityEngine.getTemplate("src/main/java/patterns/example/templates/MovieCatalog.html");

            // Генеруємо HTML
            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            // Зберігаємо HTML в файл
            File htmlFile = new File("movie_catalog.html");
            try (FileWriter fileWriter = new FileWriter(htmlFile)) {
                fileWriter.write(writer.toString());
            }

            // Відкриваємо сторінку в браузері
            Desktop.getDesktop().browse(htmlFile.toURI());

        } catch (Exception e) {
            // Обробка виключення, якщо щось піде не так
            e.printStackTrace();
        }


        //""" ПОШУК ЗА НАЗВОЮ """

        // Назва фільму, який ви хочете знайти
        String filmToFind = "Harry Potter";
        Movie foundMovie = movieCatalog.findMovieByTitle(filmToFind);

        if (foundMovie != null) {
            try {
                VelocityEngine velocityEngine = new VelocityEngine();
                velocityEngine.init();
                VelocityContext context = new VelocityContext();
                context.put("movie", foundMovie);

                Template template = velocityEngine.getTemplate("src/main/java/patterns/example/templates/Movie.html");

                StringWriter writer = new StringWriter();
                template.merge(context, writer);

                String fileName = foundMovie.getTitle() + ".html";
                File htmlFile = new File(fileName);
                try (FileWriter fileWriter = new FileWriter(htmlFile)) {
                    fileWriter.write(writer.toString());
                }
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Фільм '" + filmToFind + "' не знайдено.");
        }



        // Search for movies by category (e.g., MovieType.NEW_RELEASE)

        Movie.MovieType categoryToSearch = Movie.MovieType.NEW_RELEASE;
        java.util.List<Movie> ctgrs = movieCatalog.searchMoviesByCategory(categoryToSearch);

        for (Movie foundMovie2 : ctgrs) {
            try {
                VelocityEngine velocityEngine = new VelocityEngine();
                velocityEngine.init();
                VelocityContext context = new VelocityContext();
                context.put("movie", foundMovie2);
                // Load the Velocity template for movie details
                Template template = velocityEngine.getTemplate("src/main/java/patterns/example/templates/Movie.html");

                StringWriter writer = new StringWriter();
                template.merge(context, writer);

                // Generate and save HTML for the movie
                String fileName = foundMovie2.getTitle() + "_Details.html";
                File htmlFile = new File(fileName);
                try (FileWriter fileWriter = new FileWriter(htmlFile)) {
                    fileWriter.write(writer.toString());
                }

                // Open the HTML file in the default web browser
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        List<Rental> rentals1 = List.of(new Rental(new RegularMovie("R"), 1),
                new Rental(new  RegularMovie("Hobbits", Movie.MovieType.NEW_RELEASE), 4),
                new Rental(new  RegularMovie("Harry Potter-2", Movie.MovieType.CHILDRENS), 5));

        Customer customer1 = new Customer("John Doe", rentals1);
        String statement1 = customer1.statement();
        System.out.println("\n"+ statement1);


        List<Rental> rentals2 = List.of(
                new Rental(new NewReleaseMovie("Matrix-4"), 3),
                new Rental(new ChildrensMovie("Frozen"), 2));
        Customer customer2 = new Customer("Alice", rentals2);
        String statement2 = customer2.statement();
        System.out.println("\n***************************************\n");
        System.out.println(statement2);

        CustomerList customerList = new CustomerList();
        customerList.addCustomer(customer1);
        customerList.addCustomer(customer2);

        // Зберегти клієнтів у файл
        customerList.saveCustomersToTextFile("customers.txt");

        // Знайти клієнта за іменем
        Customer foundCustomer = customerList.findCustomerByName("John Doe");
        if (foundCustomer != null) {
            System.out.println("\n***************************************\n");
            System.out.println("Found customer: " + foundCustomer.getName());
        } else {
            System.out.println("Customer not found.");
        }
    }

}
