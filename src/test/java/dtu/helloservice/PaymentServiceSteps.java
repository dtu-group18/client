package dtu.helloservice;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceSteps {


    PaymentService service = new PaymentService();

    // ---------------------scenario 1 ----------------------------------------

    @When("a payment with customer id {string} and merchant id {string} and amount of {string} kr")
    public void aPaymentWithCustomerIdAndMerchantIdAndAmountOfKr(String cid, String mid, String amount){
        service.addCustomerAndMerchant(cid, mid);
        try{service.add(cid, mid, amount);}
        catch (NotFoundException e){System.out.println(e.getMessage());}

    }

    @Then("a payment with customer id {string} and merchant id {string} and amount of {string} kr is registered")
    public void aPaymentWithCustomerIdAndMerchantIdAndAmountOfKrIsRegistered(String cid, String mid, String amount){

        Payment p = service.get(cid , mid, amount);

        //checking that the id's and amount match for the entry recieved from the server
        assertEquals(cid, p.getCostumerId());
        assertEquals(mid, p.getMerchantId());
        assertEquals(amount, p.getAmount());

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

    @Given("a successful payment of {string} kr from customer {string} to merchant {string}")
    public void aSuccessfulPaymentOfKrFromCustomerToMerchant(String arg0, String arg1, String arg2) {

    }

    @When("a list of  payments are requested")
    public void aListOfPaymentsAreRequested() {

    }

    @Then("the list of payments contains a payment where customer {string} paid {string} kr to merchant {string}")
    public void theListOfPaymentsContainsAPaymentWhereCustomerPaidKrToMerchant(String arg0, String arg1, String arg2) {
    }
    //-----------------------------------------------------------------------


}

