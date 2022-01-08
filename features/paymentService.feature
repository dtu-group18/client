Feature: Payment

	Scenario: Successful payment
		When a payment with customer id "cid1" and merchant id "mid1" and amount of 10 kr
		Then The payment is registered

	Scenario: Remove payment
		When a payment with customer id "cid1" and merchant id "mid1" is deleted
		Then the payment does not exist in the register

	Scenario: Request payment
		When a payment with customer id "cid1" and merchant id "mid1" is requested
		Then a payment with customer id "cid1" and merchant id "mid1" is returned

	Scenario: Request list of payments
		When a list of all payments are requested
		Then a list of all the payments are returned