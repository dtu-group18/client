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
    Payment payment = service.getPayment();
    int amount;

    // scenario 1
    @Given("a customer with id {string}")
    public void aCustomerWithId(String cid1) {
        assertEquals(cid1, payment.getCostumerId());
    }

    @And("a merchant with id {string}")
    public void aMerchantWithId(String mid1) {
        assertEquals(mid1, payment.getMerchantId());
    }

    @When("the merchant initiates a payment for {int} kr by the  customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int value) {
        assertEquals(value, (int) payment.getAmount());
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertEquals(true, service.isSuccessful());
    }

    // scenario 2
    @When("a payment is deleted")
    public void aPaymentIsDeleted() {
        
    }

    @Then("the payment does not exist")
    public void thePaymentDoesNotExist() {
    }

    // scenario 3
    @When("the payment is requested")
    public void thePaymentIsRequested() {
    }

    @Then("the corresponding payment is returned")
    public void theCorrespondingPaymentIsReturned() {
    }

    // scenario 4
    @When("a list of all payments are requested")
    public void aListOfAllPaymentsAreRequested() {
    }

    @Then("a list of all the payments are returned")
    public void aListOfAllThePaymentsAreReturned() {
    }
}

