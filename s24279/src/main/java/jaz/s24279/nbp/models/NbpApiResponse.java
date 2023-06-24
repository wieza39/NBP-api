package jaz.s24279.nbp.models;

import java.util.List;

public class NbpApiResponse {

    public NbpApiResponse() {
    }

    public NbpApiResponse(String currency, String code, List<NbpRateResponse> rates) {
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }

    private String currency;
        private String code;

        private List<NbpRateResponse> rates;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<NbpRateResponse> getRates() {
            return rates;
        }

        public void setRates(List<NbpRateResponse> rates) {
            this.rates = rates;
        }
    }

