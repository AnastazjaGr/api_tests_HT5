Feature: Petstore test
#  https://petstore.swagger.io/v2/pet


  Scenario: 1 - Creating pet

    Given user has "createPetRQ" request with following parameters
      | name | status    |
      | dog  | available |

    When user sends "POST" "createPetRQ" request
    And "createPetRS" code is "200"
    And user has "getPetByIdRQ" request with id from "createPetRS" response
    And user sends "GET" "getPetByIdRQ" request

    Then "getPetByIdRS" code is "200"
    And pet name contains expected "dog"


  Scenario: 2 - Deleting pet
    Given user has "createPetRQ" request with following parameters
      | name | status    |
      | cat  | available |

    When user sends "POST" "createPetRQ" request
    And user has "deletePetRQ" request with id from "createPetRS" response
    And user sends "DELETE" "deletePetRQ" request

    Then "deletePetRS" code is "200"


  Scenario: 3 - Update pet
    Given user has "createPetRQ" request with following parameters
      | name | status    |
      | cat  | available |

    When user sends "POST" "createPetRQ" request
    And user has "updatePetRQ" request with id from "createPetRS" response and name "dog"
    And user sends "PUT" "updatePetRQ" request

    Then "updatePetRS" code is "200"
    And pet name contains expected "dog" after update
