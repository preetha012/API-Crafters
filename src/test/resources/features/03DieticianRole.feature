@DieticianRole
Feature: Dietician Role Module

  @01CreatePatient
  Scenario Outline: Check if dietician able to craete a patient.
    Given Dietician creates POST Request to create a Patient
    When Dietician sends request Body with details with "<KeyOption>" and "<Sheetname>" from excel.
    Then Dietician receives for "<KeyOption>" Patient Created Status with response body.

    Examples: 
      | KeyOption                  | Sheetname     |
      | postPatient_MissingName    | createPatient |
      | postPatient_MissingEmail   | createPatient |
      | postPatient_MissingContact | createPatient |
      | postPatient_MissingDOB     | createPatient |
      | postPatient_Valid          | createPatient |

  @02GetALLPatients-asDietician
  Scenario: Check if dietician able to retrieve all the Patients.
    Given Dietician creates GET ALL Request to retrieve all Patients
    When Dietician sends HTTPS request in Patient Module
    Then Dietician receives 200 OK Status with Patients data

  @03UpdatePatient
  Scenario Outline: Check if dietician able to Update Patient detals.
    Given Dietician creates PUT Request to Update a Patient
    When Dietician sends PUT request to update with details with "<KeyOption>" and "<Sheetname>".
    Then Dietician receives for "<KeyOption>" Patient Updated Status with response body.

    Examples: 
      | KeyOption        | Sheetname     |
      | updatePatient_Valid | createPatient |
      | updatePatient_InValid | createPatient |

  @03GetByIDPatient-asDietician
  Scenario Outline: Check if dietician able to retrieve a Patient by ID.
    Given Dietician creates GET  Request with PatientId to retrieve the Patient details.
    When Dietician sends HTTPS request with "<KeyOption>" to get patient details in  Patient Module
    Then Dietician receives Status for corresponding "<KeyOption>" Patients data

    Examples: 
      | KeyOption         |
      | PatientID_Valid   |
      | PatientID_InValid |

  @04GetByFileIDPatient-asDietician
  Scenario Outline: Check if dietician able to retrieve a Patient detail by FielID.
    Given Dietician creates GET  Request with FileID to retrieve the Patient details.
    When Dietician sends HTTPS request with "<KeyOption>" as FileID for patient.
    Then Dietician receives Status for corresponding "<KeyOption>" FieldID Patients data.

    Examples: 
      | KeyOption       |
      | FieldID_Valid   |
      | FieldID_InValid |
