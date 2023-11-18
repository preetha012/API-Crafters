
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
      
      
   @02GetALLPatients
    Scenario: Check if dietician able to retrieve all the Patients.
    Given Dietician creates GET ALL Request to retrieve all Patients
    When Dietician sends HTTPS request in Patient Module
    Then Dietician receives 200 OK Status with Patients data
    
   @03GetByIDPatient
    Scenario: Check if dietician able to retrieve a Patient by ID.
    Given Dietician creates GET  Request with PatientId to retrieve the Patient details.
    When Dietician sends HTTPS request to get patient details in  Patient Module
    Then Dietician receives 200 OK Status with Patients data
    
    @04GetByFileIDPatient
    Scenario: Check if dietician able to retrieve a Patient detail by FielID.
    Given Dietician creates GET  Request with FileID to retrieve the Patient details.
    When Dietician sends HTTPS request with FileID to get patient details in  Patient Module
    Then Dietician receives 200 OK Status with Patients data