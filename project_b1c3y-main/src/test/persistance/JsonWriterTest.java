package persistance;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// tests for JSONWriter
// code based on sample application
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            MovieList movieList = new MovieList();
            JsonWriter writer = new JsonWriter("./data/my\3ewafoihawefof.204:812hf4342\132");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // test passes
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            MovieList movieList = new MovieList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList");
            writer.open();
            writer.write(movieList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList");
            movieList = reader.read();
            assertEquals(0, movieList.getWatched().size());
            assertEquals(0, movieList.getToWatch().size());
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MovieList movieList = new MovieList();
            movieList.addMovieWatched(new Movie("Oppenheimer", 10));
            movieList.addMovieWatched(new Movie("Extraction", 9));
            movieList.addMovieWatched(new Movie("Prisoners", 10));
            movieList.addMovieToWatch(new Movie("Migration", 0));
            movieList.addMovieToWatch(new Movie("Wonka", 0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList");
            writer.open();
            writer.write(movieList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList");
            movieList = reader.read();
            assertEquals(3, movieList.getWatched().size());
            assertEquals(2, movieList.getToWatch().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
