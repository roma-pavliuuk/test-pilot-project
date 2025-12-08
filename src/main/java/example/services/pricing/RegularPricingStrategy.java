package example.services.pricing;

import example.model.Rental;
import org.springframework.stereotype.Component;

@Component("REGULAR")
public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double calculateAmount(Rental rental) {
        double amount = 2;
        if (rental.getDaysRented() > 2) {
            amount += (rental.getDaysRented() - 2) * 1.5;
        }
        return amount;
    }

    @Override
    public int calculateFrequentRenterPoints(Rental rental) {
        return 1;
    }
}