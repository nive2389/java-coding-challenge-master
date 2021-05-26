package com.crewmeister.cmcodingchallenge.currency.service;

import com.crewmeister.cmcodingchallenge.currency.dao.CurrencyDAO;
import com.crewmeister.cmcodingchallenge.currency.model.Cubes;
import com.crewmeister.cmcodingchallenge.currency.repository.CurrencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class Utility {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyRepository repo;

    final String restApiConsume1 = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";// 90 days
    final String restApiConsume = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.xml"; // all


    public List<Cubes> retrieve(JSONObject jsonObject) throws IOException {
        Map<String, Object> map = new HashMap<>();
        iterateJson(jsonObject, map);
        JSONArray jsonArray = (JSONArray) map.get("Cube");
        List<Cubes> list = new ArrayList<>();
        for (Object ar : jsonArray) {
            Cubes object = new ObjectMapper().readValue(ar.toString(), Cubes.class);
            list.add(object);
        }
        return list;
    }

    public void iterateJson(JSONObject jsonObject, Map<String, Object> map) {
        for (Object o : jsonObject.keySet()) {
            if (jsonObject.get(o.toString()) instanceof JSONObject)
                iterateJson(jsonObject.getJSONObject(o.toString()), map);
            else
                map.put(o.toString(), jsonObject.get(o.toString()));
        }
    }

    public List<CurrencyDAO> collectData() {

        RestTemplate restTemplate = new RestTemplate();
        List<CurrencyDAO> currencyList = new ArrayList<>();
        try {
            String res = restTemplate.getForObject(restApiConsume, String.class);
            if(null != res) {
                JSONObject jsonObject = XML.toJSONObject(res);
                List<Cubes> result = retrieve(jsonObject);
                result.forEach(x -> x.getCube().forEach(y -> {
                    CurrencyDAO cur = new CurrencyDAO();
                    cur.setDate(x.getTime());
                    cur.setUnit(y.getCurrency());
                    cur.setCurrencyValue(y.getRate());
                    currencyList.add(cur);
                }));
                log.info("Currency List Size: {}", currencyList.size());
            }
        } catch (ResourceAccessException rae) {
            log.info("Network Error: {}", rae.getMessage());
            //collectData();
        } catch (JSONException e) {
            log.error("JSON parsing exception: {}",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    public void restService() {
        List<CurrencyDAO> currencyList = collectData();
        if (currencyList.size() > 0) {
            repo.saveAll(currencyList);
        }
    }

    public String getDateInString(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }


    @Scheduled(cron = "${update.currencyrate.cron}")
    public void scheduleEveryDay() {
        restService();
    }
}
