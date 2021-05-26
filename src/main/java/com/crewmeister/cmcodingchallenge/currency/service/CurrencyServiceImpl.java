package com.crewmeister.cmcodingchallenge.currency.service;

import com.crewmeister.cmcodingchallenge.currency.dao.CurrencyDAO;
import com.crewmeister.cmcodingchallenge.currency.exception.NotFoundException;
import com.crewmeister.cmcodingchallenge.currency.model.CurrencyDTO;
import com.crewmeister.cmcodingchallenge.currency.model.Units;
import com.crewmeister.cmcodingchallenge.currency.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CurrencyRepository repo;
    @Autowired
    Utility util;

    List<CurrencyDAO> list = new ArrayList<>();

    @Override
    public List<String> getUnits() {

        List<String> result = repo.findDistinctUnit();
        log.info("Result: {}", result);
        return result;
    }

    @Override
    public List<CurrencyDTO> getAll()  {
        list = repo.findAll();
        return convertToDTO(list);
    }
    @Override
    public List<CurrencyDTO> getByDate(final Date date) {
        log.info("Given date: {}", date);
        String formattedDate = util.getDateInString(date);
        if (count() > 0) {
            list = repo.findAllByDate(formattedDate);
            log.info("list: {}", list);
        }
        return convertToDTO(list);
    }

    @Override
    public CurrencyDTO getByUnitAndDate(final String unit, final Date date) {
        CurrencyDTO dto = new CurrencyDTO();
            if (count() > 0) {
                String formattedDate = util.getDateInString(date);
                CurrencyDAO dao = repo.findByUnitAndDate(unit, formattedDate);
                if (null != dao) {
                    log.info("DAO: {}", dao.getId());
                    dto = convertToDTO(Collections.singletonList(dao)).get(0);
                }
            }

        return dto;
    }

    @Override
    public Long count() {
        log.info("repo count: {}", repo.count());
        return repo.count();
    }

    private List<CurrencyDTO> convertToDTO(final List<CurrencyDAO> list) {
        List<CurrencyDTO> dtoList = new ArrayList<>();
        log.info("Before Conversion List: {}", list.size());

            list.forEach(x -> {
                CurrencyDTO dto = new CurrencyDTO();
                dto.setCurrencyValue(x.getCurrencyValue());
                dto.setDate(x.getDate());
                dto.setUnit(x.getUnit());
                dto.setTimeSeries(contains(x.getUnit()) ?
                        Units.valueOf(x.getUnit()).getTimeAsString(): "TIMESERIES NOT AVAILABEL");
                 dtoList.add(dto);
            });

        log.info("After Conversion DTO List: {}", dtoList.size());
        return dtoList;
    }

    public boolean contains(String unit) {
        for (Units c : Units.values()) {
            if (c.name().equals(unit)) {
                return true;
            }
        }
        return false;
    }

}
