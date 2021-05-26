package com.crewmeister.cmcodingchallenge.currency.service;

import com.crewmeister.cmcodingchallenge.currency.dao.CurrencyDAO;
import com.crewmeister.cmcodingchallenge.currency.model.CurrencyDTO;
import com.crewmeister.cmcodingchallenge.currency.repository.CurrencyRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CurrencyServiceImplTest {

    @InjectMocks
    CurrencyServiceImpl mock;

    @Mock
    CurrencyRepository mockRepo;

    @Mock
    Utility mockUtil;

    List<CurrencyDAO> mockdao = new ArrayList<>();

    @BeforeEach
    public void setup() {
        CurrencyDAO dao = new CurrencyDAO();
        dao.setId(1L);
        dao.setDate("2021-05-10");
        dao.setCurrencyValue(1.002);
        dao.setUnit("INR");
        mockdao.add(dao);

    }

    @Test
    void getUnitsTest() {
        List<String> list = new ArrayList<>(Arrays.asList("AUS", "INR"));
        Mockito.when(mockRepo.findDistinctUnit()).thenReturn(list);
        assertEquals(mock.getUnits().size(),2);
    }
    @Test
    void getUnitsEmptyListTest() {
        List<String> list = new ArrayList<>();
        Mockito.when(mockRepo.findDistinctUnit()).thenReturn(list);
        assertEquals(mock.getUnits().size(),0);
    }

    @Test
    void getAllTest() throws Exception {
        Mockito.when(mockRepo.findAll()).thenReturn(mockdao);
        List<CurrencyDTO> list = mock.getAll();
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getUnit(), "INR");
    }
    @Test
    void getAllWithOneMissingTimeSeriesTest() throws Exception {
        CurrencyDAO dao = new CurrencyDAO();
        dao.setId(2L);
        dao.setDate("2021-05-10");
        dao.setCurrencyValue(1.002);
        dao.setUnit("GGG");
        mockdao.add(dao);

        Mockito.when(mockRepo.findAll()).thenReturn(mockdao);
        List<CurrencyDTO> list = mock.getAll();
        assertEquals(list.size(), 2);
        assertEquals(list.get(1).getUnit(), "GGG");
        assertEquals(list.get(1).getTimeSeries(), "TIMESERIES NOT AVAILABEL");

    }
    @Test
    void getAllEmptyListTest() throws Exception {
        Mockito.when(mockRepo.findAll()).thenReturn(new ArrayList<CurrencyDAO>());
        List<CurrencyDTO> list = mock.getAll();
        assertTrue(list.isEmpty());

    }

    @Test
    void getByDateTest() throws Exception {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("10-05-2021");
        Mockito.when(mockUtil.getDateInString(Mockito.any())).thenReturn("2021-05-10");
        Mockito.when(mockRepo.count()).thenReturn(1L);
        Mockito.when(mockRepo.findAllByDate(Mockito.anyString())).thenReturn(mockdao);
        List<CurrencyDTO> result = mock.getByDate(date);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getUnit(), "INR");
    }

    @Test
    void getByDateCountZeroTest() throws Exception {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("10-05-2021");
        Mockito.when(mockUtil.getDateInString(Mockito.any())).thenReturn("2021-05-10");
        Mockito.when(mockRepo.count()).thenReturn(0L);
        //Mockito.when(mockRepo.findAllByDate(Mockito.anyString())).thenReturn(mockdao);
        List<CurrencyDTO> result = mock.getByDate(date);
        assertEquals(result.size(), 0);

    }

    @Test
    void getByUnitAndDateTest() throws Exception {
        CurrencyDAO dao = new CurrencyDAO();
        dao.setId(1L);
        dao.setDate("2021-05-10");
        dao.setCurrencyValue(1.002);
        dao.setUnit("INR");
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("10-05-2021");
        Mockito.when(mockUtil.getDateInString(Mockito.any())).thenReturn("2021-05-10");
        Mockito.when(mockRepo.count()).thenReturn(1L);
        Mockito.when(mockRepo.findByUnitAndDate(Mockito.anyString(), Mockito.anyString())).thenReturn(dao);
        CurrencyDTO result = mock.getByUnitAndDate("INR", date);
        assertEquals(result.getCurrencyValue(), 1.002);
    }

    @Test
    void getByUnitAndDateNullTest() throws Exception {

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("10-05-2021");
        Mockito.when(mockUtil.getDateInString(Mockito.any())).thenReturn("2021-05-10");
        Mockito.when(mockRepo.count()).thenReturn(1L);
        Mockito.when(mockRepo.findByUnitAndDate(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        CurrencyDTO result = mock.getByUnitAndDate("INR", date);
        assertEquals(result.getDate(), null);
    }


}