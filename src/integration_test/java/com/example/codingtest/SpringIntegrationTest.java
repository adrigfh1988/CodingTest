package com.example.codingtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.client.RestClient;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    protected @LocalServerPort int port;
    protected RestClient restClient = RestClient.create();

    @Autowired
    JdbcClient jdbcClient;

    protected ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
}