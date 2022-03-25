Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using Add Place API

    Given Add Place Payload with "<name>" "<address>" "<language>" "<website>"
    When  user calls "AddPlaceAPI" with "POST" http request
    Then  the API call got success with status code 200
    And   "status" in response body is "OK"
    And   "scope" in response body is "APP"
    Then  verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | accuracy | name           | address         | language  | website            |
      | 55       | Esrat Jahan    | 30 Brooklyn     | Arabic    | http://youtube.com |
      | 65       | Fahad Kader    | 90 Queens villa | Hindi     | http://google.com  |
      | 99       | Sania Jahan    | Long Island     | Bangla    | http://ea.com      |
      | 100      | Ahad Kader     | New York        | Spanish   | http://ebay.com    |
      | 200      | Ayesha Nahar   | Bangladesh      | Farshi    | http://adidas.com  |
      | 300      | Ashikul Kader  | Dhaka   1212    | Chinese   | http://amazon.com  |
      | 400      | Salman 22 Khan | Mumbai          | Hindustan | http://bbc.com     |

    # Not able to send Integer (accuracy)  through data-driven: Learn it...!



  @DeletePlace
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When  user calls "DeletePlaceAPI" with "DELETE" http request
    Then  the API call got success with status code 200
    And   "status" in response body is "OK"
