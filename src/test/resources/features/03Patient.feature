
@Patient-Dietician
Feature: Create Patient

#Background: User loged in as Dietician
 #Given User as loged in as dietician and sends request Body with "Culture22" and "preetha012@gmail.com".


    
      
  @01PostTag
  Scenario Outline: Check if dietician able to craete a patient.
    Given Dietician creates POST Request to create a Patient
    When Dietician sends request Body with details with "<KeyOption>" and "<Sheetname>" from excel.
    Then Dietician receives Patient Created Status with response body.

    Examples: 
      | KeyOption   | Sheetname     |
      | postPatient1     | createPatient |
      
      
      