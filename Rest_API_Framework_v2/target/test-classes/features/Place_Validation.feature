

 Feature: Validating Place APIs


  Scenario: Verify if Place is being successfully added using AddPlace API
   Given  Add Place Payload
   When   User calls "AddPlaceAPI" with POST http request
   Then   The API call is success with status code 200
   And    "status" in response body is "OK"
   And    "scope" in response body is "APP"


# continue from 98 video