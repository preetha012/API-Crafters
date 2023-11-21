@DieticianLogin
Feature: Dietician Login

  @01DieticianLogin
  Scenario Outline: Check if user able to login as a Dietician
    Given User creates POST Request for login as Dietician.
    When User as dietician sends request Body with mandatory , additional fields with "<KeyOption>" and "<Sheetname>" from excel.
    Then User as dietician receives Status for corresponding "<KeyOption>" with response body.

    Examples: 
      | KeyOption              | Sheetname      |
      | DieticianLogin_InValid | DieticianLogin |
      | DieticianLogin_Valid   | DieticianLogin |
      