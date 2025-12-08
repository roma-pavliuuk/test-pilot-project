package example.services.pricing;

import example.model.Rental;
import org.springframework.stereotype.Component;

@Component("CHILDRENS")
public class ChildrensPricingStrategy implements PricingStrategy {
    @Override
    public double calculateAmount(Rental rental) {
        double amount = 1.5;
        if (rental.getDaysRented() > 3) {
            amount += (rental.getDaysRented() - 3) * 1.5;
        }
        return amount;
    }

    @Override
    public int calculateFrequentRenterPoints(Rental rental) {
        return 1;
    }
}
