package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for the Movie class
class MovieTest {
    Movie testMovie1;
    Movie testMovie2;
    Movie testMovie3;
    Movie testMovie4;

    @BeforeEach
    void runBefore() {
        testMovie1 = new Movie("Bullet Train", 9);
        testMovie2 = new Movie("Suzume", 1);
        testMovie3 = new Movie("A Trip to the Moon", 7);
        testMovie4 = new Movie("Oppenheimer", 10);
    }

    @Test
    void testConstructor () {
        assertEquals("Bullet Train", testMovie1.getName());
        assertEquals(9, testMovie1.getRating());
        assertNull(testMovie1.getDirector());
        assertNull(testMovie1.getGenre());
        assertEquals(0, testMovie1.getYear());

        assertEquals("Suzume", testMovie2.getName());
        assertEquals(1, testMovie2.getRating());
    }

    @Test
    void testChangeRating () {
        testMovie2.changeRating(10);
        assertEquals(10, testMovie2.getRating());
        testMovie2.changeRating(1);
        assertEquals(1, testMovie2.getRating());
        testMovie2.changeRating(2);
        assertEquals(2, testMovie2.getRating());
        testMovie2.changeRating(9);
        assertEquals(9, testMovie2.getRating());

        testMovie1.changeRating(8);
        assertEquals(8, testMovie1.getRating());
        testMovie1.changeRating(7);
        assertEquals(7, testMovie1.getRating());
        testMovie1.changeRating(8);
        assertEquals(8, testMovie1.getRating());
    }

    @Test
    void testSetGenre() {
        testMovie1.setGenre("Comedy");
        assertEquals("Comedy", testMovie1.getGenre());

        testMovie2.setGenre("Anime");
        assertEquals("Anime", testMovie2.getGenre());
    }

    @Test
    void testSetDirector() {
        testMovie1.setDirector("David Leitch");
        assertEquals("David Leitch", testMovie1.getDirector());

        testMovie2.setDirector("Makoto Shinkai");
        assertEquals("Makoto Shinkai", testMovie2.getDirector());
    }

    @Test
    void testSetYear() {
        testMovie1.setYear(2022);
        assertEquals(2022, testMovie1.getYear());

        testMovie3.setYear(1902);
        assertEquals(1902, testMovie3.getYear());

        testMovie4.setYear(2023);
        assertEquals(2023, testMovie4.getYear());
    }
}
