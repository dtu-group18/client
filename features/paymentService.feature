Feature: Payment

	Scenario: Successful payment
		Given a customer with id "cid1"
		And a merchant with id "mid1"
		When the merchant initiates a payment for 10 kr by the  customer
		Then the payment is sucessful

	Scenario: Remove payment
		Given a customer with id "cid1"
		And a merchant with id "mid1"
		When a payment is deleted
		Then the payment does not exist

	Scenario: Request payment
		Given a customer with id "cid1"
		And a merchant with id "mid1"
		When the payment is requested
		Then the corresponding payment is returned

	Scenario: Request list of payments
		When a list of all payments are requested
		Then a list of all the payments are returned