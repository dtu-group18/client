Feature: bank
  Scenario: Check dtu bank connects successfully
    When test dtu bank

  Scenario: Create customer account successfully
    Given a customer with a bank account with balance 1000
    Then clean up accounts

  Scenario: Get merchant
    Given get merchant

  Scenario: Successful Payment
    Given a customer with a bank account with balance 1000
    And that the customer is registered with DTU Pay
    Given a merchant with a bank account with balance 2000
    And that the merchant is registered with DTU Pay
    When the merchant initiates a payment for 100 kr by the customer.
    Then the payment is successful!
    And the balance of the customer at the bank is 900 kr
    And the balance of the merchant at the bank is 2100 kr
    Then clean up accounts

#  Scenario: Clean up
#    Then clean up accounts
