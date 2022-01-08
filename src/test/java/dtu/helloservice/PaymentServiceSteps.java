package dtu.helloservice;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceSteps {

    // Customer customer = new Customer();
    // Merchant merchant = new Merchant();
    PaymentService service = new PaymentService();
    Payment payment = service.

    int amount;

    // scenario 1

    @When("a payment with customer id {string} and merchant id {string} and amount of {int} kr")
    public void aPaymentWithCustomerIdAndMerchantIdAndAmountOfKr(String arg0, String arg1, int arg2) {

    }

    @Then("The payment is registered")
    public void thePaymentIsRegistered() {
    }

    // scenario 2
    @When("a payment with customer id {string} and merchant id {string} is deleted")
    public void aPaymentWithCustomerIdAndMerchantIdIsDeleted(String arg0, String arg1) {

    }

    @Then("the payment does not exist in the register")
    public void thePaymentDoesNotExistInTheRegister() {
    }
    // scenario 3
    @When("a payment with customer id {string} and merchant id {string} is requested")
    public void aPaymentWithCustomerIdAndMerchantIdIsRequested(String arg0, String arg1) {

    }

    @Then("a payment with customer id {string} and merchant id {string} is returned")
    public void aPaymentWithCustomerIdAndMerchantIdIsReturned(String arg0, String arg1) {
    }

    // scenario 4
    @When("a list of all payments are requested")
    public void aListOfAllPaymentsAreRequested() {
    }

    @Then("a list of all the payments are returned")
    public void aListOfAllThePaymentsAreReturned() {
    }


}

