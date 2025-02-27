@booking
Feature: Admin manages booking details

  Background:
    Given I create a new booking request

  Scenario: Admin creates booking
    When I load the body from bookingCreateRequest.json
    And I perform POST on BOOKING resource
    Then Status code is 200
    And I verify the response record matches bookingCreateResponse.json

