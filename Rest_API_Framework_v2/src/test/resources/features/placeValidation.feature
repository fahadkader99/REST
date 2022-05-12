Feature: Validating Place APIs


  @AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlace API

    Given  Add Place Payload with "<name>" "<phone_number>" "<address>" "<language>" "<website>"
    When   user calls "AddPlaceAPI" with "POST" http request
    Then   the API call is success with status code 200
    And    verify "status" in response body is "OK"
    And    verify "scope" in response body is "APP"
    Then  verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name          | phone_number | address      | language | website            |
      | ABC 67        | 1234567890   | Bronx        | English  | http://youtube.com |
      | Ash 121       | 3025109999   | Manhattan    | Bengali  | http://google.com  |
      | Kader  $      | 3472802792   | Queens       | Spanish  | http://ea.com      |
      | Fahad  #      | 6789543219   | Brooklyn     | Arabic   | http://ebay.com    |
      | Ayesha Nahar  | 1212121212   | Bangladesh   | Chinese  | http://adidas.com  |
      | Ashikul Kader | 1111111111   | Dhaka   1212 | Farsi    | http://amazon.com  |


  @DeletePlace
  Scenario: Verify if Delete Place functionality is working

    Given  DeletePlace Payload
    When   user calls "DeletePlaceAPI" with "DELETE" http request
    Then   the API call is success with status code 200
    And    verify "status" in response body is "OK"
















    # scenario in Cucumber is Test Cases:
    # always write code which which is re-usable in the future as well : like making it dynamic or "parameterized"

      # Framework goal is to make is as much re-usable as i can!!!!