package com.crewmeister.cmcodingchallenge.currency.controller;

import com.crewmeister.cmcodingchallenge.currency.constants.Constants;
import com.crewmeister.cmcodingchallenge.currency.exception.NotFoundException;
import com.crewmeister.cmcodingchallenge.currency.model.CurrencyConversionRates;
import com.crewmeister.cmcodingchallenge.currency.model.CurrencyDTO;
import com.crewmeister.cmcodingchallenge.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/api/currencies")
@Validated
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    @GetMapping("/allCurrencies")
    public ResponseEntity<?> getCurrencies() {
        List<String> list = currencyService.getUnits();
        if(list.isEmpty()) {
            return new ResponseEntity<>(Constants.NO_DATA_AVAILABEL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCurrencies() throws Exception {
        List<CurrencyDTO> list = currencyService.getAll();
        if(list.isEmpty()) {
            throw new NotFoundException(Constants.NO_DATA_AVAILABEL);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getCurrenciesByDate(@PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws Exception {
        List<CurrencyDTO> list;
        if(currencyService.count() <= 0) {
            throw new Exception(Constants.SOMETHING_WRONG);
        } else {
            list = currencyService.getByDate(date);
            if (list.isEmpty()) {
                throw new NotFoundException(Constants.INVALID_INPUT);
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{unit}/{date}")
    public ResponseEntity<?> getCurrencyByDateAndType(@PathVariable String unit, @PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws Exception {
        CurrencyDTO dto;
        if(currencyService.count() <= 0) {
            throw new Exception(Constants.SOMETHING_WRONG);
        } else {
            dto = currencyService.getByUnitAndDate(unit.toUpperCase(), date);
            if (null == dto.getUnit()) {
                throw new NotFoundException(Constants.INVALID_INPUT);
            }
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/convertCurrency")
    public ResponseEntity<?> getCurrencyConversion(@RequestParam String unit, @RequestParam double givenCurrency, @RequestParam @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws Exception {
        CurrencyConversionRates ccr;
        if(currencyService.count() <= 0) {
            throw new Exception(Constants.SOMETHING_WRONG);
        } else {
             ccr = currencyService.getConvertCurrency(unit,givenCurrency,date);
        }
        return new ResponseEntity<>(ccr, HttpStatus.ACCEPTED);
    }
}
