Feature: Validating Place APIs


  Scenario Outline: Verify if Place is being successfully added using Add Place API

    Given Add Place Payload with "<name>" "<address>" "<language>" "<website>"
    When  user calls "AddPlaceAPI" with Post http request
    Then  the API call got success with status code 200
    And   "status" in response body is "OK"
    And   "scope" in response body is "APP"

    Examples:
      | accuracy | name          | address         | language | website            |
      | 55       | mac house     | 30 Brooklyn     | Arabi    | http://youtube.com |
      | 65       | apple pie     | 90 Queens villa | Hindi    | http://google.com  |
      | 99       | moose house   | Long Island     | Bangla   | http://ea.com      |
      | 100      | Haunted villa | New York        | Spanish  | http://ebay.com    |

    # Not able to send Integer (accuracy)  through data-driven: Learn it...!