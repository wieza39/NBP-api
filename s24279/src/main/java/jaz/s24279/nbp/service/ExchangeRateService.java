package jaz.s24279.nbp.service;

import jaz.s24279.nbp.exceptions.CodeNotFound;
import jaz.s24279.nbp.exceptions.MissingData;
import jaz.s24279.nbp.exceptions.WrongDate;
import jaz.s24279.nbp.models.ExchangeRate;
import jaz.s24279.nbp.models.NbpApiResponse;
import jaz.s24279.nbp.models.NbpRateResponse;
import jaz.s24279.nbp.repository.ExchangeRateRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;


@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final RestTemplate restTemplate;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, RestTemplate restTemplate) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.restTemplate = restTemplate;
    }

    public ExchangeRate addRecord(ExchangeRate exchangeRate) {
        exchangeRateRepository.save(exchangeRate);
        return exchangeRate;
    }

    public ExchangeRate dataVerification(String code, LocalDate startDate, LocalDate endDate) {

        if (code.length() != 3 || code == null) {
            throw new CodeNotFound("Wrong currency code: " + code);
        }
        if (startDate.isAfter(endDate)) {
            throw new WrongDate("Start date: " + startDate + " occurs after end date: " + endDate);
        }
        if (startDate.isBefore(LocalDate.of(2002, 02, 01))) {
            throw new WrongDate("Archive are stored since 2002-01-02. Choose different start date");
        }

        ResponseEntity<NbpApiResponse> response =
                restTemplate.getForEntity("http://api.nbp.pl/api/exchangerates/rates/a/{code}/{startDate}/{endDate}/",
                        NbpApiResponse.class,
                        code, startDate, endDate);
        NbpApiResponse apiResponse = response.getBody();
        List<NbpRateResponse> rates = apiResponse.getRates();
        if (rates.isEmpty()) {
            throw new MissingData("Data were not found for you request");
        }

        double averageRate = rates.stream().mapToDouble(NbpRateResponse::getMid).average().getAsDouble();

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(code);
        exchangeRate.setStartDate(startDate);
        exchangeRate.setEndDate(endDate);
        exchangeRate.setExchangeRate(averageRate);

        addRecord(exchangeRate);

        return exchangeRate;
    }
}
