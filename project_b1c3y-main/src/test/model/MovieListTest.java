package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests for the MovieList class
class MovieListTest {
    MovieList testMovieList1;
    MovieList testMovieList2;
    Movie movie1;
    Movie movie2;
    Movie movie3;
    Movie movie4;
    Movie movie5;
    Movie movie6;

    @BeforeEach
    void runBefore() {
        testMovieList1 = new MovieList();
        testMovieList2 = new MovieList();
        movie1 = new Movie("Oppenheimer", 9);
        movie2 = new Movie("Spider-Man Into the Spiderverse", 10);
        movie3 = new Movie("Burn After Reading", 2);
        movie4 = new Movie("Zoolander", 1);
        movie5 = new Movie("Asteroid City", 5);
        movie6 = new Movie("1917", 10);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMovieList1.getWatched().size());
        assertEquals(0, testMovieList1.getToWatch().size());
    }

    @Test
    void testAddMovieWatched() {
        assertTrue(testMovieList1.addMovieWatched(movie2));
        assertEquals(1, testMovieList1.getWatched().size());
        assertEquals("Spider-Man Into the Spiderverse", testMovieList1.getWatched().get(0).getName());

        assertFalse(testMovieList1.addMovieWatched(movie2));
        assertEquals(1, testMovieList1.getWatched().size());

        assertTrue(testMovieList1.addMovieToWatch(movie1));
        assertTrue(testMovieList1.addMovieWatched(movie1));
        assertEquals(0, testMovieList1.getToWatch().size());
        assertEquals(2, testMovieList1.getWatched().size());
        assertEquals("Oppenheimer", testMovieList1.getWatched().get(1).getName());
    }

    @Test
    void testAddMovieToWatch() {
        assertTrue(testMovieList1.addMovieToWatch(movie1));
        assertEquals(1, testMovieList1.getToWatch().size());
        assertTrue(testMovieList1.addMovieToWatch(movie2));
        assertTrue(testMovieList1.addMovieToWatch(movie3));
        assertTrue(testMovieList1.addMovieToWatch(movie4));
        assertTrue(testMovieList1.addMovieToWatch(movie5));
        assertEquals(5, testMovieList1.getToWatch().size());

        assertFalse(testMovieList1.addMovieToWatch(movie1));
        assertEquals(5, testMovieList1.getToWatch().size());
    }

    @Test
    void testSortMovies() {
        testMovieList1.addMovieWatched(movie1);
        testMovieList1.addMovieWatched(movie2);
        testMovieList1.addMovieWatched(movie3);
        testMovieList1.sortMovies(testMovieList1.getWatched());
        assertEquals("Spider-Man Into the Spiderverse", testMovieList1.getWatched().get(0).getName());
        assertEquals("Oppenheimer", testMovieList1.getWatched().get(1).getName());
        assertEquals("Burn After Reading", testMovieList1.getWatched().get(2).getName());

        testMovieList2.addMovieToWatch(movie2);
        testMovieList2.addMovieToWatch(movie3);
        testMovieList2.addMovieToWatch(movie1);
        testMovieList2.addMovieToWatch(movie4);
        testMovieList2.addMovieToWatch(movie5);
        testMovieList2.addMovieToWatch(movie6);
        testMovieList2.sortMovies(testMovieList2.getToWatch());
        assertEquals("Spider-Man Into the Spiderverse", testMovieList2.getToWatch().get(0).getName());
        assertEquals("1917", testMovieList2.getToWatch().get(1).getName());
        assertEquals("Oppenheimer", testMovieList2.getToWatch().get(2).getName());
        assertEquals("Asteroid City", testMovieList2.getToWatch().get(3).getName());
        assertEquals("Burn After Reading", testMovieList2.getToWatch().get(4).getName());
        assertEquals("Zoolander", testMovieList2.getToWatch().get(5).getName());
    }

    @Test
    void testFindMovie() {
        testMovieList2.addMovieWatched(movie4);
        testMovieList2.addMovieWatched(movie5);
        testMovieList2.addMovieWatched(movie6);
        testMovieList2.findMovie("1917", testMovieList2.getWatched());
        assertEquals("1917", testMovieList2.findMovie("1917", testMovieList2.getWatched()).getName());
        assertEquals(10, testMovieList2.findMovie("1917", testMovieList2.getWatched()).getRating());

        assertNull(testMovieList2.findMovie("Oppenheimer", testMovieList2.getWatched()));

        assertNull(testMovieList2.findMovie("Oppenheimer", testMovieList2.getToWatch()));
        testMovieList2.addMovieToWatch(movie1);
        assertEquals("Oppenheimer", testMovieList2.findMovie("Oppenheimer",
                testMovieList2.getToWatch()).getName());
        assertEquals(9, testMovieList2.findMovie("Oppenheimer",
                testMovieList2.getToWatch()).getRating());
        testMovieList2.addMovieToWatch(movie2);
        assertEquals("Spider-Man Into the Spiderverse",
                testMovieList2.findMovie("Spider-Man Into the Spiderverse",
                        testMovieList2.getToWatch()).getName());
        assertEquals(10, testMovieList2.findMovie("Spider-Man Into the Spiderverse",
                testMovieList2.getToWatch()).getRating());
    }

    @Test
    void testToString() {
        testMovieList2.addMovieWatched(movie1);
        testMovieList2.addMovieWatched(movie2);
        testMovieList2.addMovieWatched(movie3);
        ArrayList<String> watchedMovies = new ArrayList<>();
        watchedMovies.add("Oppenheimer");
        watchedMovies.add("Spider-Man Into the Spiderverse");
        watchedMovies.add("Burn After Reading");
        assertEquals(watchedMovies.toString(), testMovieList2.toString("watched"));

        testMovieList2.addMovieWatched(movie4);
        testMovieList2.addMovieWatched(movie5);
        watchedMovies.add("Zoolander");
        watchedMovies.add("Asteroid City");
        assertEquals(watchedMovies.toString(), testMovieList2.toString("watched"));


        testMovieList1.addMovieToWatch(movie1);
        testMovieList1.addMovieToWatch(movie2);
        ArrayList<String> toWatchMovies = new ArrayList<>();
        toWatchMovies.add("Oppenheimer");
        toWatchMovies.add("Spider-Man Into the Spiderverse");
        assertEquals(toWatchMovies.toString(), testMovieList1.toString("to watch"));

        assertNull(testMovieList2.toString("watch"));
        assertNull(testMovieList1.toString("towatch"));

        ArrayList<String> empty = new ArrayList<>();
        assertEquals(empty.toString(), testMovieList1.toString("watched"));
        assertEquals(empty.toString(), testMovieList2.toString("to watch"));
    }
}