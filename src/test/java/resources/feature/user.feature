Feature: Testing Users features on GoRest Application

  Scenario: Check if the GoRest application can be accessed by users
    When User sends a GET request to users endpoint
    Then User must get back a valid status code 200

  Scenario: Create new user and verify if the user is added
    When User creates a new user by providing the information name "<name>" gender "<gender>" email "<email>" status "<status>"
    Then User verifies that the user is created

  Scenario: Update the created user and verify user is updated with status 200
    When User updates created user by providing the information name "<name>" email "<email>"
    Then User verifies user is updated

  Scenario: Delete created user using id
    When User delete created user using id
    Then User verifies user is deleted with status code 204