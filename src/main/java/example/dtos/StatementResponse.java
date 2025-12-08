package example.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatementResponse {
    private String customerName;
    private List<RentalDetail> rentals;
    private double totalAmount;
    private int frequentRenterPoints;

    @Data
    @Builder
    public static class RentalDetail {
        private String movieTitle;
        private double amount;
    }
}
