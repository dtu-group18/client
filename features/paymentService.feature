Feature: Payment

	Scenario: Successful Payment
		Given a customer with id "cid1" and a merchant with id "mid1"
		When the merchant initiates a payment for "10" kr by the customer
		Then the payment is successful

	Scenario: List of payments
		Given a successful payment of "10" kr from customer "cid1" to merchant "mid1"
		When the manager asks for a list of payments
	    Then the list of payments contains a payment where customer "cid1" paid "10" kr to merchant "mid1"

	Scenario: Customer is not known
		Given a new customer with id "cid2" and merchant with id "mid1"
	    When the merchant initiates a payment for "10" kr by the new customer
		Then the payment is not successful
		Then an unknown costumer error message is returned saying "costumer with id cid2 is unknown"

	Scenario: Merchant is not known
		Given a customer with id "cid1" and a new merchant with id "mid2"
		When the new merchant initiates a payment for "10" kr by the customer
		Then an unknown merchant error message is returned saying "merchant with id mid2 is unknown"