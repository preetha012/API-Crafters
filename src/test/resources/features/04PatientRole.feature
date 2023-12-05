
@PatientRole
Feature: Patient Role

    @01PatientRole-Login
    Scenario Outline: Check if user able to login as a Patient
    Given User creates POST Request for login as Patient.
    When User as patient sends request Body with mandatory , additional fields with "<KeyOption>" and "<Sheetname>" from excel.
    Then User as Patient receives Status with response body.

    Examples: 
      | KeyOption   | Sheetname     |
      | PatientLogin     | PatientUserLogin |
 
    @02PatientRole-Invalid_Password
    Scenario Outline: Check if user able to login as a Patient with invalid password
    Given User creates POST Request for login as Patient.
    When User as patient sends request Body with invalid password "<KeyOption>" and "<Sheetname>" from excel.
    Then User as Patient receives Status with response body invalid password.  

    Examples: 
      | KeyOption   | Sheetname     |
      | InvalidPassword     | PatientUserLogin |   
      
    @03PatientRole-Invalid_Email 
    Scenario Outline: Check if user able to login as a Patient with invalid email
    Given User creates POST Request for login as Patient.
    When User as patient sends request Body with invalid email "<KeyOption>" and "<Sheetname>" from excel.
    Then User as Patient receives Status with response body invalid email.

    Examples: 
      | KeyOption   | Sheetname     |
      | InvalidEmail     | PatientUserLogin |
      
      
     @04PatientRole-Get_Report_By_Valid_patientId
     Scenario Outline: Check if patient can get report using valid patientId-PatientRole
     Given Patient user is logged inPatientRole
     When  User sends get request with valid patientID T04
     Then  User can see report for PatientId PatientRole
     
     @05PatientRole-Get_Report_By_Valid_fileId
     Scenario Outline: Check if patient can get report using valid fileId PatientRole
     Given Patient user is logged inPatientRole
     When  User sends get request with valid fileID PatientRole
     Then  User can see report for fileID PatientRole
     
     @06PatientRole-Get_Report_By_Inalid_patientId
     Scenario Outline: Check if patient can get report using invalid patientId-PatientRole
     Given Patient user is logged inPatientRole
     When  User sends get request with invalid patientID PatientRole
     Then  User can see error for invalid PatientId PatientRole
     
     @07PatientRole-Get_Report_By_Inalid_patientId
     Scenario Outline: Check if patient can get report using invalid fileId-PatientRole
     Given Patient user is logged inPatientRole
     When  User sends get request with invalid fileID PatientRole
     Then  User can see error for invalid fileId PatientRole
     
     