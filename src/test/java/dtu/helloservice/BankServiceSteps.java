package dtu.helloservice;

import fastmoney.BankService;
import fastmoney.BankServiceException_Exception;
import fastmoney.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import fastmoney.BankServiceService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;

public class BankServiceSteps {
    //BankServiceUnused bsU = new BankServiceUnused();
    BankServiceService dtuBank = new BankServiceService();

    static String customerAccountIdentifier;
    static User customer;
    static String merchantAccountIdentifier;
    static User merchant;

    @Given("a customer with a bank account with balance {int}")
    public void aCustomerWithABankAccountWithBalance(BigDecimal balance){
        //Getting customer with balance
        customer.setCprNumber("c-cpr");
        customer.setFirstName("c-fn");
        customer.setLastName("c-ln");

        try {
            customerAccountIdentifier = dtuBank.getBankServicePort().createAccountWithBalance(customer, balance);
        } catch (BankServiceException_Exception bsException){
            bsException.printStackTrace();
        }

        // Assert account is created
        Assert.assertNotNull(customerAccountIdentifier);
    }

    @And("that the customer is registered with DTU Pay")
    public void thatTheCustomerIsRegisteredWithDTUPay() {

    }

    @Given("a merchant with a bank account with balance {int}")
    public void aMerchantWithABankAccountWithBalance(BigDecimal balance) {
        merchant.setCprNumber("m-cpr");
        merchant.setFirstName("m-fn");
        merchant.setLastName("m-ln");

        try {
            merchantAccountIdentifier = dtuBank.createAccountWithBalance(merchant, balance);
        } catch (BankServiceException_Exception bsException){
            bsException.printStackTrace();
        }

        // Assert account is created
        Assert.assertNotNull(merchantAccountIdentifier);
    }

    @And("that the merchant is registered with DTU Pay")
    public void thatTheMerchantIsRegisteredWithDTUPay() {

    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int arg0) {

    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {

    }

    @And("the balance of the custoemr at the bank is {int} kr")
    public void theBalanceOfTheCustoemrAtTheBankIsKr(int arg0) {

    }

    @And("the balance of the merchant at the bank is {int} kr")
    public void theBalanceOfTheMerchantAtTheBankIsKr(int arg0) {
        cleanupAccount();
    }

    /**
     * Clean up account from bank service
     */
    private void cleanupAccount(){
        try {
            dtuBank.getBankServicePort().retireAccount(customerAccountIdentifier);
            dtuBank.getBankServicePort().retireAccount(merchantAccountIdentifier);
        } catch (BankServiceException_Exception bsException){
            bsException.printStackTrace();
        }
    }
}
