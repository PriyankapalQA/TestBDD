Feature: Abc
	
@TC1 @regression
Scenario: TC1_

 ##  Login to the portal and Create user##

 Given I open "abc" application

 
 #Given I am re-directed to "Login page"
 #When I enter "Username"
 #And I enter "Password"
 #And I click on "Submit"
 Given I am re-directed to "HomePage"
 
 And I wait till the page loads
 Given I enter "FirstName"
 And I enter "MiddleName"
 And I enter "LastName"
 And I enter "PhoneNumber"
 And I enter "EmailAddress"
 And I select "Role" from dropdown
 And I select "ContactOption"

 When I click on "CreateNewUserButton"

 Then "PopUpMsg" should be displayed as "ExpectedPopUpMsg"
 And I Logout from the application
 
 
 