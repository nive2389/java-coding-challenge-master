package com.crewmeister.cmcodingchallenge.currency.controller;

import com.crewmeister.cmcodingchallenge.currency.exception.NotFoundException;
import com.crewmeister.cmcodingchallenge.currency.model.CurrencyDTO;
import com.crewmeister.cmcodingchallenge.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>("NO DATA AVAILABLE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCurrencies() throws Exception {
        List<CurrencyDTO> list = currencyService.getAll();
        if(list.isEmpty()) {
            throw new NotFoundException("NO DATA AVAILABLE");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getCurrenciesByDate(@PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws Exception {
        List<CurrencyDTO> list;
        if(currencyService.count() <= 0) {
            throw new Exception("Something went wrong. Please try again later.");
        } else {
            list = currencyService.getByDate(date);
            if (list.isEmpty()) {
                throw new NotFoundException("Ensure the given date should not be a weekend");
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{unit}/{date}")
    public ResponseEntity<?> getCurrencyByDateAndType(@PathVariable String unit, @PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws Exception {
        if(currencyService.count() <= 0) {
            throw new Exception("Something went wrong. Please try again later.");
        } else {
            CurrencyDTO dto = currencyService.getByUnitAndDate(unit.toUpperCase(), date);
            if (null == dto.getUnit()) {
                throw new NotFoundException("Ensure the given date should not be a weekend");
            }
        }
        return new ResponseEntity<>(currencyService.getByUnitAndDate(unit,date), HttpStatus.OK);
    }
}
