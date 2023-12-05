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