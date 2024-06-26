Feature: Retrieve an author

  Background:
  Given the following users exist

      | 1    | Test user          |
      | 2    | Test user 2        |

  Scenario Outline: Client finds an author that exists on DB by id
    When The client retrieves the author by <id>
    Then The client receives status code of 200
    And The client receives author info with <id> and "<name>"

    Examples:
      | id   | name               |
      | 1    | Test user          |
      | 2    | Test user 2        |



  Scenario: Client tries to find an author that does not exist on DB by id
    When The client retrieves the author by 3
    Then The client receives status code of 404