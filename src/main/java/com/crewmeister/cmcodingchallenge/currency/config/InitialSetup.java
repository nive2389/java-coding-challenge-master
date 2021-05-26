package com.crewmeister.cmcodingchallenge.currency.config;

import com.crewmeister.cmcodingchallenge.currency.service.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Profile("!test")
public class InitialSetup implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Utility util;


    @Override
    public void run(String... args) {
        log.info("Loading Data");
        util.restService();
    }


}
