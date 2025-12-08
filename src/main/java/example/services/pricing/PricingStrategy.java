package example.services.pricing;

import example.model.Rental;

public interface PricingStrategy {
    double calculateAmount(Rental rental);
    int calculateFrequentRenterPoints(Rental rental);
}
