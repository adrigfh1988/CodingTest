package com.example.codingtest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/integration_test/resources/features", plugin = {"pretty", "html:target/report.html"})
public class CucumberIntegrationTest {
}