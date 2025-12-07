package example;

import org.junit.Before;
import org.junit.Test;

import static example.Movie.MovieType.REGULAR;
import static org.junit.Assert.assertEquals;

public class RentalTest {
    private Movie movie;

    @Before
    public void setMovie() {
        movie = new Movie("Test Movie", REGULAR);
    }

    @Test
    public void testRentalCreation() {
        Rental rental = new Rental(movie, 3);
        assertEquals(movie, rental.getMovie());
        assertEquals(3, rental.getDaysRented());
    }

    @Test
    public void testRentalWithDifferentDays() {
        Rental rental1 = new Rental(movie, 1);
        Rental rental5 = new Rental(movie, 5);

        assertEquals(1, rental1.getDaysRented());
        assertEquals(5, rental5.getDaysRented());
    }
}
