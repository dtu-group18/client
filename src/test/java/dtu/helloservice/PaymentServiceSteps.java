package dtu.helloservice;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceSteps {


    PaymentService service = new PaymentService();


    // ---------------------scenario 1 ----------------------------------------

    @When("a payment with customer id {string} and merchant id {string} and amount of {int} kr")
    public void aPaymentWithCustomerIdAndMerchantIdAndAmountOfKr(String cid, String mid, int amount){

        try{service.add(cid, mid, (double)amount);}
        catch (NotFoundException e){System.out.println(e.getMessage());}

    }

    @Then("a payment with customer id {string} and merchant id {string} and amount of {int} kr is registered")
    public void aPaymentWithCustomerIdAndMerchantIdAndAmountOfKrIsRegistered(String cid, String mid, int amount){

    }

    //-------------------------------------------------------------------------

    // ---------------------scenario 2 ----------------------------------------
    @When("a payment with customer id {string} and merchant id {string} is deleted")
    public void aPaymentWithCustomerIdAndMerchantIdIsDeleted(String cid, String mid) {

    }

    @Then("the payment does not exist in the register")
    public void thePaymentDoesNotExistInTheRegister() {
    }

    //------------------------------------------------------------------------

    // ---------------------scenario 3 ---------------------------------------
    @When("a payment with customer id {string} and merchant id {string} is requested")
    public void aPaymentWithCustomerIdAndMerchantIdIsRequested(String cid, String mid) {

    }

    @Then("a payment with customer id {string} and merchant id {string} is returned")
    public void aPaymentWithCustomerIdAndMerchantIdIsReturned(String cid, String mid) {
    }

    //-----------------------------------------------------------------------

    // ---------------------scenario 4 --------------------------------------
    @When("a list of all payments are requested")
    public void aListOfAllPaymentsAreRequested() {
    }

    @Then("a list of all the payments are returned")
    public void aListOfAllThePaymentsAreReturned() {
    }

    //-----------------------------------------------------------------------


}

