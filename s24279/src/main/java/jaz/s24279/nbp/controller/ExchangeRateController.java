package jaz.s24279.nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jaz.s24279.nbp.models.ExchangeRate;
import jaz.s24279.nbp.models.NbpApiResponse;
import jaz.s24279.nbp.models.NbpRateResponse;
import jaz.s24279.nbp.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/kolokwium")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Operation(summary = "Gets currency rate average for indicated dates and saves request to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful"),
            @ApiResponse(responseCode = "404", description = "Currency not found"),
            @ApiResponse(responseCode = "400", description = "Missing data")
    })
    @GetMapping("/get/{code}")
    public ResponseEntity<ExchangeRate> getRateFromApi(@PathVariable("code") String code,
                                                       @RequestParam("startDate") LocalDate startDate,
                                                       @RequestParam("endDate") LocalDate endDate) {

        ExchangeRate exchangeRateRecod = exchangeRateService.dataVerification(code, startDate, endDate);
        return ResponseEntity.ok(exchangeRateRecod);

    }
}
