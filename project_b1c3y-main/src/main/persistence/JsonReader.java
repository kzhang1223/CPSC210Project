package persistence;

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// JSON reader for reading files
// code based on sample application
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from give source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads workroom from file and returns it;
    //          throws IOException if an error occurs when reading data
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: parses movie list from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        MovieList movieList = new MovieList();
        addWatchList(movieList, jsonObject);
        addToWatchList(movieList, jsonObject);
        return movieList;
    }

    // MODIFIES: movieList
    // EFFECTS: parses the watched list from JSON object and adds them to movieList
    private void addWatchList(MovieList movieList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("watched");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addMovieWatched(movieList, nextThingy);
        }
    }

    // MODIFIES: movieList
    // EFFECTS: parses the to watch list from JSON object and adds them to movieList
    private void addToWatchList(MovieList movieList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("to watch");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addMovieToWatch(movieList, nextThingy);
        }
    }

    // MODIFIES: movieList
    // EFFECTS: parses a movie in the watched list from JSON object and adds it to movieList
    private void addMovieWatched(MovieList movieList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rating = jsonObject.getInt("rating");
        Movie movie = new Movie(name, rating);
        movieList.addMovieWatched(movie);
    }

    // MODIFIES: movieList
    // EFFECTS: parses a movie in the to watch list from JSON object and adds it to movieList
    private void addMovieToWatch(MovieList movieList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rating = jsonObject.getInt("rating");
        Movie movie = new Movie(name, rating);
        movieList.addMovieToWatch(movie);
    }
}

