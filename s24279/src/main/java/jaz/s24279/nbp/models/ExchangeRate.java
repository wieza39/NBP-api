package jaz.s24279.nbp.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ExchangeRateRequests")
public class ExchangeRate {

    public ExchangeRate() {
    }

    public ExchangeRate(Long id, String currency, LocalDate startDate, LocalDate endDate, double exchangeRate, LocalDateTime requestDate) {
        this.id = id;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.exchangeRate = exchangeRate;
        this.requestDate = requestDate;
    }

    @Id
    @Schema(description = "numer porzadkowy")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "waluta")
    @Schema(description = "symbol waluty")
    private String currency;

    @Column(name = "startDate")
    @Schema(description = "data początkowa szukanego zakresu w formacie YYYY-MM-DD")
    private LocalDate startDate;

    @Column(name = "endDate")
    @Schema(description = "data końcowa szukanego zakresu w formacie YYYY-MM-DD")
    private LocalDate endDate;

    @Column(name = "kurs")
    @Schema(description = "kurs wskazanej waluty")
    private double exchangeRate;

    @Column(name = "data zapytania")
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(description = "data zapytania")
    private LocalDateTime requestDate;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
