package example.services;

import example.model.Customer;
import example.model.Movie;
import example.model.Rental;
import example.repository.CustomerRepository;
import example.repository.MovieRepository;
import example.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitializerService {
    private final MovieRepository movieRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public DataInitializerService(MovieRepository movieRepository,
                                  CustomerRepository customerRepository,
                                  RentalRepository rentalRepository) {
        this.movieRepository = movieRepository;
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public void initializeData() {
        if (movieRepository.count() == 0) {
            List<Movie> movies = List.of(
                    new Movie(null, "Harry Potter", Movie.MovieType.REGULAR),
                    new Movie(null, "Avengers: Endgame", Movie.MovieType.NEW_RELEASE),
                    new Movie(null, "Frozen", Movie.MovieType.CHILDRENS)
            );
            movieRepository.saveAll(movies);
            System.out.println("Movies table initialized!");
        }

        if (customerRepository.count() == 0) {
            List<Customer> users = List.of(
                    new Customer(null, "Alice", new ArrayList<>()),
                    new Customer(null, "Bob", new ArrayList<>())
            );
            customerRepository.saveAll(users);
            System.out.println("Customers table initialized!");
        }

//        if (rentalRepository.count() == 0) {
//            List<Rental> rentals = List.of(
//                    new Rental(null, 1L),
//                            3, LocalDate.now()),
//                    new Rental(null, customerRepository.findById(2L).orElse(null),
//                            movieRepository.findById(2L).orElse(null),
//                            1, LocalDate.now())
//            );
//            rentalRepository.saveAll(rentals);
//            System.out.println("Rentals table initialized!");
//        }
    }
}
