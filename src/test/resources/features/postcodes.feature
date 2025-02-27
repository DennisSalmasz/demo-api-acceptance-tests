@postcodes
Feature: User manages booking details

  Background:
    Given I create a new postcodes request

  Scenario: Admin creates booking
    When I load the body from postcodesCreateRequest.json
    And I perform POST on POSTCODES resource
    Then Status code is 200
    And I verify the response record matches postcodesCreateResponse.json

  Scenario: User retrieves postcodes
    When I perform GET on POSTCODES_ID resource with path parameters
      | key  | value    |
      | id   | SW1P4JA  |
    Then Status code is 200
    And I verify the response record matches postcodesResponse.json