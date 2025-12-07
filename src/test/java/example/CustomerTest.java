package example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static example.Movie.MovieType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerTest {
    private Movie regularMovie;
    private Movie newReleaseMovie;
    private Movie childrensMovie;

    @Before
    public void setUp() {
        regularMovie = new Movie("Regular Movie", REGULAR);
        newReleaseMovie = new Movie("New Release", NEW_RELEASE);
        childrensMovie = new Movie("Kids Movie", CHILDRENS);
    }

    @Test
    public void testCustomerName() {
        Customer customer = new Customer("John Doe", List.of());
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testEmptyRentals() {
        Customer customer = new Customer("John Doe", List.of());
        String statement = customer.statement();

        assertTrue(statement.contains("Rental Record for John Doe"));
        assertTrue(statement.contains("Amount owed is 0.0"));
        assertTrue(statement.contains("You earned 0 frequent renter points"));
    }

    @Test
    public void testSingleRegularRental_OneDayOnly() {
        Rental rental = new Rental(regularMovie, 1);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Regular Movie\t2.0"));
        assertTrue(statement.contains("Amount owed is 2.0"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testSingleRegularRental_TwoDays() {
        Rental rental = new Rental(regularMovie, 2);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Regular Movie\t2.0"));
        assertTrue(statement.contains("Amount owed is 2.0"));
    }

    @Test
    public void testSingleRegularRental_ThreeDays() {
        Rental rental = new Rental(regularMovie, 3);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Regular Movie\t3.5"));
        assertTrue(statement.contains("Amount owed is 3.5"));
    }

    @Test
    public void testSingleRegularRental_FiveDays() {
        Rental rental = new Rental(regularMovie, 5);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Regular Movie\t6.5"));
        assertTrue(statement.contains("Amount owed is 6.5"));
    }

    @Test
    public void testSingleNewReleaseRental_OneDay() {
        Rental rental = new Rental(newReleaseMovie, 1);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("New Release\t3.0"));
        assertTrue(statement.contains("Amount owed is 3.0"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void testSingleNewReleaseRental_TwoDays() {
        Rental rental = new Rental(newReleaseMovie, 2);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("New Release\t6.0"));
        assertTrue(statement.contains("Amount owed is 6.0"));
        assertTrue(statement.contains("You earned 2 frequent renter points"));
    }

    @Test
    public void testSingleNewReleaseRental_FourDays() {
        Rental rental = new Rental(newReleaseMovie, 4);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("New Release\t12.0"));
        assertTrue(statement.contains("Amount owed is 12.0"));
        assertTrue(statement.contains("You earned 2 frequent renter points"));
    }

    @Test
    public void testSingleChildrensRental_OneDay() {
        Rental rental = new Rental(childrensMovie, 1);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Kids Movie\t1.5"));
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void testSingleChildrensRental_ThreeDays() {
        Rental rental = new Rental(childrensMovie, 3);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Kids Movie\t1.5"));
        assertTrue(statement.contains("Amount owed is 1.5"));
    }

    @Test
    public void testSingleChildrensRental_FourDays() {
        Rental rental = new Rental(childrensMovie, 4);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Kids Movie\t3.0"));
        assertTrue(statement.contains("Amount owed is 3.0"));
    }

    @Test
    public void testSingleChildrensRental_FiveDays() {
        Rental rental = new Rental(childrensMovie, 5);
        Customer customer = new Customer("John Doe", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.contains("Kids Movie\t4.5"));
        assertTrue(statement.contains("Amount owed is 4.5"));
    }

    @Test
    public void testMultipleRentals() {
        List<Rental> rentals = List.of(
                new Rental(regularMovie, 1),
                new Rental(newReleaseMovie, 4),
                new Rental(childrensMovie, 5)
        );

        Customer customer = new Customer("John Doe", rentals);
        String statement = customer.statement();

        assertTrue(statement.contains("Regular Movie\t2.0"));
        assertTrue(statement.contains("New Release\t12.0"));
        assertTrue(statement.contains("Kids Movie\t4.5"));
        assertTrue(statement.contains("Amount owed is 18.5"));

        assertTrue(statement.contains("You earned 4 frequent renter points"));
    }

    @Test
    public void testMultipleNewReleaseRentals() {
        Movie movie1 = new Movie("New 1", NEW_RELEASE);
        Movie movie2 = new Movie("New 2", NEW_RELEASE);

        List<Rental> rentals = List.of(
                new Rental(movie1, 1),
                new Rental(movie2, 2)
        );

        Customer customer = new Customer("Test", rentals);
        String statement = customer.statement();

        assertTrue(statement.contains("You earned 3 frequent renter points"));
    }

    @Test
    public void testStatementFormat() {
        Rental rental = new Rental(regularMovie, 1);
        Customer customer = new Customer("Jane Smith", List.of(rental));
        String statement = customer.statement();

        assertTrue(statement.startsWith("Rental Record for Jane Smith\n"));
        assertTrue(statement.contains("\t"));
        assertTrue(statement.endsWith("frequent renter points"));
    }

    @Test
    public void testComplexScenario() {
        Movie reg1 = new Movie("Action Movie", REGULAR);
        Movie reg2 = new Movie("Drama", REGULAR);
        Movie newRel1 = new Movie("Blockbuster 1", NEW_RELEASE);
        Movie newRel2 = new Movie("Blockbuster 2", NEW_RELEASE);
        Movie kids = new Movie("Cartoon", CHILDRENS);

        List<Rental> rentals = List.of(
                new Rental(reg1, 5),
                new Rental(reg2, 2),
                new Rental(newRel1, 3),
                new Rental(newRel2, 1),
                new Rental(kids, 4)
        );

        Customer customer = new Customer("Big Renter", rentals);
        String statement = customer.statement();

        assertTrue(statement.contains("Amount owed is 23.5"));

        assertTrue(statement.contains("You earned 6 frequent renter points"));
    }
}