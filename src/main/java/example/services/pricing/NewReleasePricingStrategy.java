package example.services.pricing;

import example.model.Rental;
import org.springframework.stereotype.Component;

@Component("NEW_RELEASE")
public class NewReleasePricingStrategy implements PricingStrategy{
    @Override
    public double calculateAmount(Rental rental) {
        return rental.getDaysRented() * 3;
    }

    @Override
    public int calculateFrequentRenterPoints(Rental rental) {
        return rental.getDaysRented() > 1 ? 2 : 1;
    }
}
