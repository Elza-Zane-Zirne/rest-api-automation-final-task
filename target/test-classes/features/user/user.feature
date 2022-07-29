Feature: user

#  @run @positive
#    Scenario Outline: get_user_list
#    When the user gets user list
#    Then the user gets status code "<statusCode>"
#    Examples:
#      | statusCode |
#      | 200        |


  @run @positive
  Scenario Outline: create_new_user
    Given the user is authorised
    When the user creates a new user with the following values email "<email>", name "<name>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    And the user saves response key "id" as session variable with name "userId"
    And the user deletes created user
    Then the user gets status code "204"
    Examples:
    |email           | name  | gender | status |
    |janis@email.com | Janis | male   | active |

  @run @positive
    Scenario Outline: update_user
    Given the user is authorised
    When the user creates a new user with the following values email "<email>", name "<name>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    And the user saves response key "id" as session variable with name "userId"
    When the user updates created user with new email "<newEmail>", new name "<newName>" and new status "<newStatus>"
    And the following values are present in the body
      | email  | <newEmail> |
      | name   | <newName>  |
      | status | <newStatus>|
    Then the user gets status code "200"
    And the user deletes created user
    Then the user gets status code "204"
    Examples:
    |email             | name    | gender   | status   | newEmail         | newName | newStatus |
    |liene@email.com   | Liene   | female   | active   | anna@email.com   | Anna    | inactive  |
    |peteris@email.com | Peteris | male     | inactive | karlis@email.com | Karlis  | active    |

  @run @negative
  Scenario Outline: create_user_with_taken_email
    Given the user is authorised
    When the user creates a new user with the following values email "<email>", name "<name>", gender "<gender>" and status "<status>"
    Then the user gets status code "201"
    And the user saves response key "id" as session variable with name "userId"
    When the user creates a new user with the following values email "<email>", name "<newName>", gender "<newGender>" and status "<newStatus>"
    Then the following values are present in the body
      | field   | [email]                  |
      | message | [has already been taken] |
    And the user gets status code "422"
    And the user deletes created user
    Then the user gets status code "204"
    Examples:
      | email           | name  | gender | status | newName | newGender | newStatus |
      | marta@email.com | Marta | female | active | Arta    | female    | inactive  |
      | juris@email.com | Juris | male   | active | Dairis  | male      | active    |