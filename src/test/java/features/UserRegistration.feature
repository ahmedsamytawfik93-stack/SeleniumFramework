Feature: User Registration
	i WANT TO CHECK THAT THE USER CAN REGISTER IN OUR E-COMMERCE WEBSITE.
	
Scenario Outline: User Registration
Given  The user in the home page
When I click on register link
And I entered "<firstname>", "<lastname>", "<email>", "<password>"
Then The registration page displayed successfully

Examples:
	| firstname | lastename | email | password |
	| ahmed | mohamed | ahmed@test.com | 12345678 |
	| Moataz | aHMED | TEST@user.com | 3521487 |