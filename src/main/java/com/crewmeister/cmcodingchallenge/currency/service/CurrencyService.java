package com.crewmeister.cmcodingchallenge.currency.service;


import com.crewmeister.cmcodingchallenge.currency.model.CurrencyConversionRates;
import com.crewmeister.cmcodingchallenge.currency.model.CurrencyDTO;

import java.util.Date;
import java.util.List;


public interface CurrencyService {
    List<String> getUnits();
    List<CurrencyDTO> getAll() throws Exception;
    List<CurrencyDTO> getByDate(Date date) throws Exception;
    CurrencyDTO getByUnitAndDate(String unit, Date date) throws Exception;
    Long count();
    CurrencyConversionRates getConvertCurrency(String unit, double currency, Date date);
}
