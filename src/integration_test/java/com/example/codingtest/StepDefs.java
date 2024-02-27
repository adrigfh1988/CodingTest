package com.example.codingtest;

import com.example.codingtest.application.rest.response.PriceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.docstring.DocString;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StepDefs extends SpringIntegrationTest {

    ResponseEntity<PriceResponse> apiResponse;

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) {
        assertThat(apiResponse.getStatusCode().value()).isEqualTo(statusCode);
    }

    @When("the client calls the API with the following Information product-id = {int} and brand-id = {int} and date-applied = {string}")
    public void theClientCallsTheAPIWithTheFollowingInformationProductIdAndBrandIdAndDateApplied(int productId, int brandId, String date) {

        apiResponse = restClient.get()
                .uri("http://localhost:" + port + "/prices?product-id=" + productId + "&brand-id=" + brandId + "&date-applied=" + date)
                .retrieve()
                .toEntity(PriceResponse.class);


    }

    @Given("Add the following data to the Database")
    public void addTheFollowingDataToTheDatabase(DocString sql) {


        jdbcClient
                .sql(sql.getContent())
                .update();
    }

    @And("the response body is")
    public void theResponseBodyIs(DocString expectedResult) throws JsonProcessingException, JSONException {

        String writeValueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiResponse.getBody());
        JSONAssert.assertEquals(writeValueAsString,expectedResult.getContent(), writeValueAsString, JSONCompareMode.STRICT);
    }

    @Given("Clearing all the table records")
    public void clearingAllTheTableRecords() {
        jdbcClient
                .sql("TRUNCATE TABLE PRICES")
                .update();

    }
}