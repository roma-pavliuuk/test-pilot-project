package example.services;

import example.dtos.StatementResponse;
import example.model.Customer;
import example.model.Rental;
import example.repository.CustomerRepository;
import example.services.pricing.PricingStrategy;
import example.services.pricing.PricingStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalStatementService {
    private final PricingStrategyFactory pricingStrategyFactory;
    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public StatementResponse generateStatement(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        List<StatementResponse.RentalDetail> rentalDetails = customer.getRentals()
                .stream()
                .map(this::calculateRentalDetail)
                .collect(Collectors.toList());

        double totalAmount = rentalDetails.stream()
                .mapToDouble(StatementResponse.RentalDetail::getAmount)
                .sum();

        int totalPoints = customer.getRentals()
                .stream()
                .mapToInt(this::calculatePoints)
                .sum();

        return StatementResponse.builder()
                .customerName(customer.getName())
                .rentals(rentalDetails)
                .totalAmount(totalAmount)
                .frequentRenterPoints(totalPoints)
                .build();
    }

    private StatementResponse.RentalDetail calculateRentalDetail(Rental rental) {
        PricingStrategy strategy = pricingStrategyFactory
                .getStrategy(rental.getMovie().getPriceCode());

        return StatementResponse.RentalDetail.builder()
                .movieTitle(rental.getMovie().getTitle())
                .amount(strategy.calculateAmount(rental))
                .build();
    }

    private int calculatePoints(Rental rental) {
        PricingStrategy strategy = pricingStrategyFactory
                .getStrategy(rental.getMovie().getPriceCode());
        return strategy.calculateFrequentRenterPoints(rental);
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findByIdWithRentals(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public String formatStatement(StatementResponse statement) {
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(statement.getCustomerName()).append("\n");

        statement.getRentals().forEach(rental ->
                result.append("\t")
                        .append(rental.getMovieTitle())
                        .append("\t")
                        .append(rental.getAmount())
                        .append("\n")
        );

        result.append("Amount owed is ").append(statement.getTotalAmount()).append("\n");
        result.append("You earned ")
                .append(statement.getFrequentRenterPoints())
                .append(" frequent renter points");

        return result.toString();
    }
}
