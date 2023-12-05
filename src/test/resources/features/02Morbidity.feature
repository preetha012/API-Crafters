<<<<<<< HEAD
Feature: Morbidity Controller

Background: User logged in as Dietician with authorization

@M1
Scenario: Get Morbidity data
Given I set GET Morbidity end point 
When I set request header
And send GET HTTP request
Then I receive valid HTTP response code 200 

@M2
Scenario: Get Morbidity Name
Given I set GET MorbidityName end point 
When I set request header
And send GET HTTP request
Then I receive valid HTTP response code 200 
=======
@morbidityModule
Feature: Check Morbidity

@GetAllMorbidity
Scenario: Check if user able to retrieve all Morbidities
Given User creates GET  Request for  All  all Morbidities
When User sends HTTPS Request
Then User receives 200 OK Status with response body.     


@GetMorbiditybyName
Scenario Outline: Retrieve Morbidity condition by valid morbidityName
Given User creates GET Request for the API endpoint with valid "<morbidityName>"
When User sends HTTPS Reques
Then User receives 200 OK Status with response  body.                                                                  Eg: [

Examples: 
      | morbidityName  | 
      | TSH | 
      | Fasting Glucose |
      |Plasma Glucose |
      |T3 |
      |HbA1c |
      | Average Glucose |
      
@GetMorbiditybyInvalidName
Scenario Outline: retrieve Morbidity condition by Invalid morbidityname
Given User creates GET Request for the API endpoint with Invalid "<InvalidmorbidityName>"
When User sends HTTPS Reques
Then User receives 404  Status                                                                   Eg: [

Examples: 
			|InvalidmorbidityName|
      | T  | 
      | Xct | 
      |A |
			|S |
			|Se |


      
      
       
>>>>>>> ce525924e548847390bbcf66bb781413bae2ade2
