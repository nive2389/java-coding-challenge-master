package com.crewmeister.cmcodingchallenge.currency.model;

import org.springframework.stereotype.Component;

@Component
public class CurrencyConversionRates {
    private double conversionRate;
    private String converted_unit;
    private String date;

    public String getUnit() {
        return converted_unit;
    }

    public void setUnit(String unit) {
        this.converted_unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CurrencyConversionRates() {
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}