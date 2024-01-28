package model;

import org.json.JSONObject;

// Movie represents a movie with a name, rating, genre, director, year. The name and rating are mandatory but the other
// categories are set to null but can be changed later. The rating can also be changed once it is set.
// code based on sample application
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Movie {
    private final String name;
    private int rating; // a rating out of 10, 0 means unreviewed, 1 is the worst and 10 is the best
    private String genre;
    private String director;
    private int year;

    // REQUIRES: rating to be between 0 and 10 inclusive where 0 means its unreviewed
    // EFFECTS: creates a movie entry with given name and rating, genre and director set as null, and year as 0
    public Movie(String name, int rating) {
        this.name = name;
        this.rating = rating;
        genre = null;
        director = null;
        year = 0;
    }

    // REQUIRES: rating to be between 1 and 10 inclusive
    // MODIFIES: this
    // EFFECTS: gives the movie the given rating
    public void changeRating(int rating) {
        this.rating = rating;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    // REQUIRES: a 4 digit year between 1902 - the current year
    // MODIFIES: this
    // EFFECTS: sets given year for this movie
    public void setYear(int year) {
        this.year = year;
    }

    // EFFECTS: converts movie to json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("genre", genre);
        json.put("director", director);
        json.put("year", year);
        return json;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }
}
