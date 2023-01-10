Feature: Run CRUD operations on GoRest Application
  User should be able create, read, update and delete user details

  Scenario Outline: CRUD Test
    Given GoRest application is running
    When  User creates a new user by name "<name>" gender "<gender>" email "<email>" status "<status>"
    Then User verifies that the user with name "<name>" is created
    And User updates created user by providing the information name "<name>" email "<email>"
    And User with name "<name>" is updated successfully
    And User delete created user using name "<name>"
    Then User is deleted successfully from the application

    Examples:
      | name        | gender | email           | status |
      | Ami Mathur  | female | ami1@example.com | active |
      | Shi Thim    | Male   | shi1@example.com | inactive |