package dtu.helloservice;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceSteps {
    PaymentService service = new PaymentService();
    boolean successfulPayment;
    String cid, mid;
    List<Payment> paymentList;
    String unknownCostumerErrMsg;
    String unknownMerchantErrMsg;

    // ---------------------Successful Payment----------------------------------------

    @Given("a customer with id {string} and a merchant with id {string}")
    public void iHaveCostumerAndMerchant(String cid, String mid) {
        this.cid = cid;
        this.mid = mid;
    }

    @When("the merchant initiates a payment for {string} kr by the customer")
    public void initiatePayment(String amount) {
        try {
            successfulPayment = service.add(cid, mid, amount);
        } catch (NotFoundException e) {
            successfulPayment = false;
            System.out.println(e.getMessage());
        }
    }

    @Then("the payment is successful")
    public void ifPaymentSuccessful() {
        Assert.assertTrue(successfulPayment);
    }

    //-------------------------------------------------------------------------


    // ---------------------List of payments--------------------------------------

    @Given("a successful payment of {string} kr from customer {string} to merchant {string}")
    public void getPayment(String amount, String cid, String mid) {
        Payment p = service.get(cid, mid, amount);

        assertEquals(cid, p.getCostumerId());
        assertEquals(mid, p.getMerchantId());
        assertEquals(amount, p.getAmount());
    }

    @When("the manager asks for a list of payments")
    public void getPayments() {
        paymentList = service.getPaymentsList();
    }

    @Then("the list of payments contains a payment where customer {string} paid {string} kr to merchant {string}")
    public void ifListContainsPayment(String cid, String amount, String mid) {
        Payment paymentFromList = null;
        for(Payment p: paymentList) {
            if (p.getAmount().equals(amount) && p.getCostumerId().equals(cid) && p.getMerchantId().equals(mid)) {
                paymentFromList = p;
                break;
            }
        }
        assertNotNull(paymentFromList);
    }

    //-----------------------------------------------------------------------

    // ---------------------Customer is not known ----------------------------------------
    @Given("a new customer with id {string} and merchant with id {string}")
    public void iHaveNewCostumer(String cid2, String mid) {
        this.cid = cid2;
        this.mid = mid;
    }

    @When("the merchant initiates a payment for {string} kr by the new customer")
    public void addPaymentForNewCostumer(String amount) {
        try {
            service.add(cid, mid, amount);
        } catch (NotFoundException e) {
            successfulPayment = false;
            unknownCostumerErrMsg = e.getMessage();
        }
    }

    @Then("the payment is not successful")
    public void ifPaymentNotSuccessful() {
        assertEquals(false, successfulPayment);
    }

    @Then("an unknown costumer error message is returned saying {string}")
    public void checkUnknownCostumerErrMsg(String errorMsg) {
        assertEquals(unknownCostumerErrMsg, errorMsg);
    }

    //------------------------------------------------------------------------


    // ---------------------Merchant is not known ----------------------------------------
    @Given("a customer with id {string} and a new merchant with id {string}")
    public void iHaveNewMerchant(String cid, String mid2) {
        this.cid = cid;
        this.mid = mid2;
    }

    @When("the new merchant initiates a payment for {string} kr by the customer")
    public void addPaymentForNewMerchant(String amount) {
        try {
            service.add(cid, mid, amount);
        } catch (NotFoundException e) {
            successfulPayment = false;
            unknownMerchantErrMsg = e.getMessage();
        }
    }

    @Then("an unknown merchant error message is returned saying {string}")
    public void checkUnknownMerchantErrMsg(String errorMsg) {
        assertEquals(unknownMerchantErrMsg, errorMsg);
    }

    //------------------------------------------------------------------------

}

