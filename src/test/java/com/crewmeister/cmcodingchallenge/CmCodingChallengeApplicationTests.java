package com.crewmeister.cmcodingchallenge;

import com.crewmeister.cmcodingchallenge.currency.config.InitialSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@ActiveProfiles("test")
class CmCodingChallengeApplicationTests {

   @Test
    void contextLoads() {

    }

}
