package example;

import org.junit.Test;

import static example.Movie.MovieType.CHILDRENS;
import static example.Movie.MovieType.NEW_RELEASE;
import static example.Movie.MovieType.REGULAR;
import static org.junit.Assert.assertEquals;

public class MovieTest {
    @Test
    public void testMovieCreation() {
        Movie movie = new Movie("Test Movie", REGULAR);
        assertEquals("Test Movie", movie.getTitle());
        assertEquals(REGULAR, movie.getPriceCode());
    }

    @Test
    public void testAllMovieTypes() {
        Movie regular = new Movie("Regular", REGULAR);
        Movie newRelease = new Movie("New", NEW_RELEASE);
        Movie childrens = new Movie("Kids", CHILDRENS);

        assertEquals(REGULAR, regular.getPriceCode());
        assertEquals(NEW_RELEASE, newRelease.getPriceCode());
        assertEquals(CHILDRENS, childrens.getPriceCode());
    }
}
