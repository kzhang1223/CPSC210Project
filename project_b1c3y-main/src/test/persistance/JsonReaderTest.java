package persistance;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for JSONReader
// code based on sample application
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader test1 = new JsonReader("./data/nonExistentFile.json");
        try {
            MovieList movieList = test1.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader test1 = new JsonReader("./data/testReaderEmptyMovieList");
        ArrayList<Movie> empty = new ArrayList<>();
        try {
            MovieList movieList = test1.read();
            assertEquals(empty, movieList.getWatched());
            assertEquals(empty, movieList.getToWatch());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader test1 = new JsonReader("./data/testReaderGeneralMovieList");
        try {
            MovieList movieList = test1.read();
            assertEquals(3, movieList.getWatched().size());
            assertEquals(2, movieList.getToWatch().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
