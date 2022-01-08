package dtu.helloservice;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaymentServiceSteps {

    // Customer customer = new Customer();
    // Merchant merchant = new Merchant();
    Payment payment = new Payment();
    PaymentService service = new PaymentService();
    int amount;

    // scenario 1
    @Given("a customer with id {string}")
    public void aCustomerWithId(String cid1) {

    }

    @And("a merchant with id {string}")
    public void aMerchantWithId(String mid1) {
    }

    @When("the merchant initiates a payment for {int} kr by the  customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int value) {

    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
    }
}
