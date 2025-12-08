package example.controller;

import example.dtos.StatementResponse;
import example.services.RentalStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalStatementService statementService;

    @GetMapping("/customers/{customerId}/statement")
    public ResponseEntity<StatementResponse> getStatement(@PathVariable Long customerId) {

        StatementResponse statement = statementService.generateStatement(customerId);
        return ResponseEntity.ok(statement);
    }

    @GetMapping("/customers/{customerId}/statement/text")
    public ResponseEntity<String> getStatementText(@PathVariable Long customerId) {

        StatementResponse statement = statementService.generateStatement(customerId);
        String formattedStatement = statementService.formatStatement(statement);

        return ResponseEntity.ok(formattedStatement);
    }
}
