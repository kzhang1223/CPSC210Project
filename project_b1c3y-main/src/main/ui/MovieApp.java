package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Interacts with the user and gains input for each method and processes what the method is supposed to
public class MovieApp {
    private static final String JSON_STORE = "./data/movielist.json";
    private MovieList mainList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // code partially based on TellerApp and JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public MovieApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runApplication();
    }

    // MODIFIES: this
    // EFFECTS: processes the input from the user
    private void runApplication() {
        String command;

        init();

        while (true) {
            menu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                break;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Good morning, and in case it's not morning, "
                + "good afternoon, good evening and good night!");
    }

    // MODIFIES: this
    // EFFECTS: initializes movie list
    private void init() {
        mainList = new MovieList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints the menu of items the user can choose from to do
    private void menu() {
        System.out.println("What do you want to:");
        System.out.println("\ttype a to add a movie");
        System.out.println("\ttype r to review a movie");
        System.out.println("\ttype v to view all movies in a list");
        System.out.println("\ttype s to sort all movies in a list");
        System.out.println("\ttype save to save current movie lists");
        System.out.println("\ttype load to load previous movie lists");
        System.out.println("\ttype q to quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addMovie();
        } else if (command.equals("r")) {
            reviewMovie();
        } else if (command.equals("v")) {
            viewMovie();
        } else if (command.equals("s")) {
            sortMovies();
        } else if (command.equals("save")) {
            saveMovies();
        } else if (command.equals("load")) {
            loadMovies();
        } else {
            System.out.println("I don't know what that means.");
        }
    }

    // REQUIRES: movie being added shouldn't be in the list its being added to
    // MODIFIES: this
    // EFFECTS: adds movie with a name and rating to the list the user wants to add to
    private void addMovie() {
        System.out.println("Do you want to add to watched or to watch?");
        String list = input.next();

        if (list.equals("watched")) {
            System.out.println("What is the name of the movie you want to add?");
            String movie = input.next();
            System.out.println("Give a rating: 0 for no rating, or a number between 1 and 10.");
            int rating = input.nextInt();
            Movie newMovie = new Movie(movie, rating);
            mainList.addMovieWatched(newMovie);
            System.out.println(movie + " added. :D");
        } else if (list.equals("to watch")) {
            System.out.println("What is the name of the movie you want to add?");
            String movie = input.next();
            System.out.println("Give a rating: 0 for no rating, or a number between 1 and 10.");
            int rating = input.nextInt();
            Movie newMovie = new Movie(movie, rating);
            mainList.addMovieToWatch(newMovie);
            System.out.println(movie + " added. :D");
        } else {
            System.out.println("Please enter watched or to watch.");
        }
    }

    // MODIFIES: this
    // EFFECTS: reviews inputted movie in the watched list
    private void reviewMovie() {
        System.out.println("What movie do you want to review?");
        String movie = input.next();
        System.out.println("What is the rating you want to give it?");
        int rating = input.nextInt();
        Movie movieToReview = mainList.findMovie(movie, mainList.getWatched());
        if (movieToReview != null) {
            movieToReview.changeRating(rating);
            System.out.println(movie + " reviewed. :D");
        } else {
            System.out.println("You haven't watched that movie...");
        }
    }

    // EFFECTS: prints all the movies in the chosen movie list
    private void viewMovie() {
        System.out.println("Do you want to see your watched or to watch list?");
        String list = input.next();
        if (list.equals("watched")) {
            System.out.println("Your watched movies: " + mainList.toString(list));
        } else if (list.equals("to watch")) {
            System.out.println("Your to watch movies: " + mainList.toString(list));
        } else {
            System.out.println("I don't know what list that is...");
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts the movies in the inputted movie list by rating
    private void sortMovies() {
        System.out.println("Do you want to sort your watched or to watch list? "
                + "This will keep the list sorted by rating.");
        String list = input.next();
        if (list.equals("watched")) {
            mainList.sortMovies(mainList.getWatched());
            System.out.println("Your watched list is now sorted.");
        } else if (list.equals("to watch")) {
            mainList.sortMovies(mainList.getToWatch());
            System.out.println("Your to watch list is now sorted.");
        } else {
            System.out.println("Sorry I have no clue what you are saying...");
        }
    }

    // EFFECTS: saves movie list to file
    private void saveMovies() {
        try {
            jsonWriter.open();
            jsonWriter.write(mainList);
            jsonWriter.close();
            System.out.println("Saved your movies to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movie list from file
    private void loadMovies() {
        try {
            mainList = jsonReader.read();
            System.out.println("Loaded movie list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
