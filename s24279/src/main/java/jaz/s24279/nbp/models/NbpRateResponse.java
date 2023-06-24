package jaz.s24279.nbp.models;

import java.time.LocalDate;

public class NbpRateResponse {

    public NbpRateResponse() {
    }

    public NbpRateResponse(LocalDate rateDate, double mid) {
        this.rateDate = rateDate;
        this.mid = mid;
    }


    private LocalDate rateDate;
    private double mid;

    public LocalDate getRateDate() {
        return rateDate;
    }

    public void setRateDate(LocalDate rateDate) {
        this.rateDate = rateDate;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

}
