Feature: validating Dietician Delete and Logout request
This feature is to validate logout dietician url 

#Background: User logged in as Dietician with authorization

@01Del
  Scenario: Verify that patient id is deleted using valid url
      Given Dietician user set Delete request for valid patientId url
      When Dietician user send delete request for valid patientId 
      Then the response will return delete status 200
 


@logout
  Scenario: Check if user able to logout as a Dietician
    Given Dietician user set GET Request for logout as Dietician.
    When Dietician user send logout request
    Then the response will return status 200

  