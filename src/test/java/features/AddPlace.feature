Feature:validation of Place's APIs

  @AddPlace @Regression
  Scenario Outline: Verify user is successfully able to add a place using AddPlace API
    Given AddPlace API payload with "<name>" "<Address>" "<Language>"
    When user calls the "AddPlaceAPI" API with "POST" http request
    Then API call got success with status code 200
    And  "status" in response body is "OK"
    And  "scope" in response body is "APP"
    And verify place_id created maps to the "<name>" using "GetPlaceAPI"

    Examples:
    |name  |Address|Language|
    |Apurva|Yerwada|English|
    #|Ajinkya|Delhi |Hindi|

@DeletePlace @Regression
  Scenario: Verify if Delete API functionality is working properly
    Given DeletPlace API payload
    When user calls the "DeletePlaceAPI" API with "POST" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"