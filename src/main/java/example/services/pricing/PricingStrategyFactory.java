package example.services.pricing;

import example.model.Movie;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PricingStrategyFactory {
    private final Map<String, PricingStrategy> strategies;

    public PricingStrategyFactory(Map<String, PricingStrategy> strategies) {
        this.strategies = strategies;
    }

    public PricingStrategy getStrategy(Movie.MovieType movieType) {
        PricingStrategy strategy = strategies.get(movieType.name());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown movie type: " + movieType);
        }
        return strategy;
    }
}
