Feature: Payment

	Scenario: Request payment
		When a payment with customer id "cid1" and merchant id "mid1" is requested
		Then a payment with customer id "cid1" and merchant id "mid1" is returned

	Scenario: Successful payment
		When a payment with customer id "cid1" and merchant id "mid1" and amount of "10" kr
		Then a payment with customer id "cid1" and merchant id "mid1" and amount of "10" kr is registered

	Scenario: Remove payment
		When a payment with customer id "cid1" and merchant id "mid1" is deleted
		Then the payment does not exist in the register


	Scenario: List of payments
		Given a successful payment of "10" kr from customer "cid1" to merchant "mid1"
		When a list of  payments are requested
		Then the list of payments contains a payment where customer "cid1" paid "10" kr to merchant "mid1"


	Scenario: Customer not known

	Scenario: Merchant not known