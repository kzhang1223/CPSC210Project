package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

// Stores the movies in either the watched or to watch list, also adds, sorts, and finds movies within either list.
// code based on sample application
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class MovieList {
    private final ArrayList<Movie> watched;
    private final ArrayList<Movie> toWatch;

    // EFFECTS: create 2 empty lists for movies watched and to watch
    public MovieList() {
        watched = new ArrayList<>();
        toWatch = new ArrayList<>();
    }

    public ArrayList<Movie> getWatched() {
        return watched;
    }

    public ArrayList<Movie> getToWatch() {
        return toWatch;
    }

    // MODIFIES: this
    // EFFECTS: adds given movie to given movie list and returns true, if the movie was in the to watch list and
    //          is being added to the watched list it removes the movie from the to watch list;
    //          if movie is already in list, it does nothing and returns false
    public boolean addMovieWatched(Movie movie) {
        if (!this.getWatched().contains(movie)) {
            this.getWatched().add(movie);
            this.getToWatch().remove(movie);
            EventLog.getInstance().logEvent(new Event("Movie added to watch list"));
            return true;
        } else {
            return false;
        }
    }

    //
    // MODIFIES: this
    // EFFECTS: adds given movie to given movie list and returns true,
    //          if movie is already in list, it does nothing and returns false
    public boolean addMovieToWatch(Movie movie) {
        if (!this.getToWatch().contains(movie)) {
            this.getToWatch().add(movie);
            EventLog.getInstance().logEvent(new Event("Movie added to to watch list"));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts the given list by rating from highest to lowest, when ratings are the same, sorts by
    //          alphabetical order, titles with numbers first go after the alphabet
    public void sortMovies(ArrayList<Movie> movieList) {
        movieList.sort(Comparator.comparing(Movie::getRating).reversed());
        EventLog.getInstance().logEvent(new Event("Movies in watched list sorted"));
    }

    // REQUIRES: the movie list must not be empty
    // EFFECTS: looks for given movie in given movie list
    public Movie findMovie(String movieName, ArrayList<Movie> movieList) {
        Movie foundMovie = null;
        for (Movie movie : movieList) {
            if (movie.getName().equals(movieName)) {
                foundMovie = movie;
            }
        }
        return foundMovie;
    }

    // EFFECTS: returns a string of the list of movies
    public String toString(String movieList) {
        ArrayList<String> movies = new ArrayList<>();
        if (movieList.equals("watched")) {
            for (Movie movie : this.getWatched()) {
                String movieName = movie.getName();
                movies.add(movieName);
            }
        } else if (movieList.equals("to watch")) {
            for (Movie movie : this.getToWatch()) {
                String movieName = movie.getName();
                movies.add(movieName);
            }
        } else {
            return null;
        }
        return movies.toString();
    }

    // EFFECTS: converts movie list to json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("watched", watchedToJson());
        json.put("to watch", toWatchToJson());
        return json;
    }

    // EFFECTS: converts watched list to json
    private JSONArray watchedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie movie : watched) {
            jsonArray.put(movie.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: converts to watch list to json
    private JSONArray toWatchToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie movie : toWatch) {
            jsonArray.put(movie.toJson());
        }

        return jsonArray;
    }
}
