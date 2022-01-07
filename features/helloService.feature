Feature: hello service

	Scenario: hello service returns correct answer
		When I call the hello service
		Then I get the answer "Hello RESTEasy"


	Scenario: hello service returns the correct person
		When I call the hello service asking for a person
		Then I get a person with name "karsten" and address "Mjølnerparken 8"