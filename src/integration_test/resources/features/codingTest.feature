Feature: Feature to validate the API behaviour mentioned in the Coding Test

  Background:
    Given Clearing all the table records
    Given Add the following data to the Database
    """sql
     insert into PRICES
     values (1, 1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 1, 35455, 0, 35.50, 'EUR'),
            (2, 1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 2, 35455, 1, 25.45, 'EUR'),
            (3, 1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 3, 35455, 1, 30.50, 'EUR'),
            (4, 1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 4, 35455, 1, 38.95, 'EUR');
    """

  Scenario:  Test 1: petición a las 10:00 del día 14 del producto 35455  para la brand 1 (ZARA)
    When the client calls the API with the following Information product-id = 35455 and brand-id = 1 and date-applied = "2020-06-14 10:00:00"
    Then the client receives status code of 200
    And the response body is
    """json
    {
      "productId": 35455,
      "brandId": 1,
      "appliedFee": 1,
      "startDate":"2020-06-14 00:00:00",
      "endDate":"2020-12-31 23:59:59",
      "finalPriceToApply": 35.50
    }
    """

  Scenario:  Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    When the client calls the API with the following Information product-id = 35455 and brand-id = 1 and date-applied = "2020-06-14 16:00:00"
    Then the client receives status code of 200
    And the response body is
    """json
    {
      "productId": 35455,
      "brandId": 1,
      "appliedFee": 2,
      "startDate":"2020-06-14 15:00:00",
      "endDate":"2020-06-14 18:30:00",
      "finalPriceToApply": 25.45
    }
    """

  Scenario:  Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    When the client calls the API with the following Information product-id = 35455 and brand-id = 1 and date-applied = "2020-06-14 21:00:00"
    Then the client receives status code of 200
    And the response body is
    """json
    {
      "productId": 35455,
      "brandId": 1,
      "appliedFee": 1,
      "startDate":"2020-06-14 00:00:00",
      "endDate":"2020-12-31 23:59:59",
      "finalPriceToApply": 35.50
    }
    """

  Scenario:  Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    When the client calls the API with the following Information product-id = 35455 and brand-id = 1 and date-applied = "2020-06-15 10:00:00"
    Then the client receives status code of 200
    And the response body is
    """json
    {
      "productId": 35455,
      "brandId": 1,
      "appliedFee": 3,
      "startDate":"2020-06-15 00:00:00",
      "endDate":"2020-06-15 11:00:00",
      "finalPriceToApply": 30.50
    }
    """

  Scenario:  Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    When the client calls the API with the following Information product-id = 35455 and brand-id = 1 and date-applied = "2020-06-16 21:00:00"
    Then the client receives status code of 200
    And the response body is
    """json
    {
      "productId": 35455,
      "brandId": 1,
      "appliedFee": 4,
      "startDate":"2020-06-15 16:00:00",
      "endDate":"2020-12-31 23:59:59",
      "finalPriceToApply": 38.95
    }
    """