package example.repository;

import example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.rentals r LEFT JOIN FETCH r.movie WHERE c.id = :id")
    Optional<Customer> findByIdWithRentals(Long id);

    Optional<Customer> findByName(String name);
}
