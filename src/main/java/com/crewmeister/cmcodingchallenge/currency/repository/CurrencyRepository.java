package com.crewmeister.cmcodingchallenge.currency.repository;

import com.crewmeister.cmcodingchallenge.currency.dao.CurrencyDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyDAO, Long> {

    @Query("SELECT DISTINCT a.unit FROM CurrencyDAO a")
    List<String> findDistinctUnit();

    List<CurrencyDAO> findAll();

    List<CurrencyDAO> findAllByDate(String date);

    CurrencyDAO findByUnitAndDate(String unit, String date);


}
