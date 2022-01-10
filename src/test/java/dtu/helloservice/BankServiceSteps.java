package dtu.helloservice;

import dtu.ws.fastmoney.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankServiceSteps {
    BankService dtuBank = new BankServiceService().getBankServicePort();
    PaymentService dtuSimplePay = new PaymentService();
    CustomerService customerService = new CustomerService();
    MerchantService merchantService = new MerchantService();
    PaymentService paymentService = new PaymentService();

    static String customerAccountIdentifier;
    static String merchantAccountIdentifier;
    static User customer;
    static User merchant;
    static String customerId;
    static String merchantId;
    static int transferAmount;
    boolean successfulPayment;

    @When("test dtu bank")
    public void check() throws BankServiceException_Exception {
        try {
            List<AccountInfo> list = dtuBank.getAccounts();
            for (AccountInfo a: list) {
                System.out.println(a.getAccountId());
                System.out.println(a.getUser().getCprNumber());
                System.out.println(a.getUser().getFirstName());
                System.out.println(a.getUser().getLastName());
                if ((a.getUser().getCprNumber().equals("c-cpr")) ||( a.getUser().getCprNumber().equals("m-cpr"))){
                    dtuBank.retireAccount(a.getAccountId());
                }
            }
        } catch (Exception bsException){
            System.out.println(bsException.getMessage());
        }
    }

    @Given("a customer with a bank account with balance {int}")
    public void aCustomerWithABankAccountWithBalance(int balance){
        // Init customer
        customer = new User();
        customer.setCprNumber("c-cpr");
        customer.setFirstName("c-fn");
        customer.setLastName("c-ln");

        try {
            //Getting customer with balance
            BigDecimal bigBalance = BigDecimal.valueOf(balance);
            customerAccountIdentifier = dtuBank.createAccountWithBalance(customer, bigBalance);
        } catch (BankServiceException_Exception bsException){
            bsException.printStackTrace();

            if (bsException.getMessage().equals("Account already exists")) {
                System.out.println(customerAccountIdentifier);
            }
        }

        // Assert account is created
        Assert.assertNotNull(customerAccountIdentifier);
    }

    @And("that the customer is registered with DTU Pay")
    public void thatTheCustomerIsRegisteredWithDTUPay() {
        try {
            customerId = customerService.register(customer.getFirstName() + " " + customer.getLastName(), customer.getCprNumber(), customerAccountIdentifier);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNotNull(customerId);

        // Get customer
        try {
            Customer c = customerService.get(customerId);
            Assert.assertEquals(customer.getCprNumber(), c.getCpr());
            Assert.assertEquals(customerAccountIdentifier, c.getBankAccount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Given("a merchant with a bank account with balance {int}")
    public void aMerchantWithABankAccountWithBalance(int balance) {
        merchant = new User();
        merchant.setCprNumber("m-cpr");
        merchant.setFirstName("m-fn");
        merchant.setLastName("m-ln");

        try {
            BigDecimal bigBalance = BigDecimal.valueOf(balance);
            merchantAccountIdentifier = dtuBank.createAccountWithBalance(merchant, bigBalance);
        } catch (BankServiceException_Exception bsException){
            bsException.printStackTrace();
            System.out.println(bsException.getMessage());
        }

        // Assert account is created
        Assert.assertNotNull(merchantAccountIdentifier);
    }

    @And("that the merchant is registered with DTU Pay")
    public void thatTheMerchantIsRegisteredWithDTUPay() {
        try {
            merchantId = merchantService.register(merchant.getFirstName() + " " + merchant.getLastName(), merchant.getCprNumber(), merchantAccountIdentifier);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNotNull(merchantId);

        // Get merchant
        try {
            Merchant m = merchantService.get(merchantId);
            Assert.assertEquals(merchant.getCprNumber(), m.getCpr());
            Assert.assertEquals(merchantAccountIdentifier, m.getBankAccount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @When("the merchant initiates a payment for {int} kr by the customer.")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(int amount) {
        //Initiate payment from merchant to customer
        try {
            transferAmount = amount;
            successfulPayment = paymentService.add(customerId, merchantId, String.valueOf(transferAmount));
        } catch (NotFoundException e) {
            successfulPayment = false;
        }

    }

    @Then("the payment is successful!")
    public void thePaymentIsSuccessful() {
        assertTrue(successfulPayment);

        // Transfer
//        try {
//
//            dtuBank.transferMoneyFromTo(customerAccountIdentifier, merchantAccountIdentifier, BigDecimal.valueOf(transferAmount), "Transfer money");
//        } catch (BankServiceException_Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @And("the balance of the customer at the bank is {int} kr")
    public void theBalanceOfTheCustomerAtTheBankIsKr(int customerBalance) {
//            BigDecimal b =  dtuBank.getAccount(customerAccountIdentifier).getBalance();
            assertEquals(customerBalance, customerService.getBalance(customerId));
    }

    @And("the balance of the merchant at the bank is {int} kr")
    public void theBalanceOfTheMerchantAtTheBankIsKr(int merchantBalance) {
//            BigDecimal b =  dtuBank.getAccount(merchantAccountIdentifier).getBalance();
            assertEquals(merchantBalance, merchantService.getBalance(merchantId));

    }

    /**
     * This step is supposed to be called from all scenarios
     *  that produce accounts, as their last step.
     */
    @When("clean up accounts")
    public void cleanup() {
        cleanupAccount();
    }

    /**
     * Clean up account from bank service
     */
    private void cleanupAccount(){
        // Delete customer account
        try {
            // Remove registration from simple pay
            customerService.delete(customerId);

            // Delete from dtu bank
            dtuBank.retireAccount(customerAccountIdentifier);

            // Trigger account not exists error
            dtuBank.getAccount(customerAccountIdentifier);
        } catch (BankServiceException_Exception bsException) {
            // Assert that account is deleted
            Assert.assertEquals("Account does not exist", bsException.getMessage());
        }

        // Delete merchant account
        try {
            // Remove registration from simple pay
            merchantService.delete(merchantId);

            // Delete from dtu bank
            dtuBank.retireAccount(merchantAccountIdentifier);

            // Trigger account not exists error
            dtuBank.getAccount(merchantAccountIdentifier);
        } catch (BankServiceException_Exception bsException) {
            // Assert that account is deleted
            Assert.assertEquals("Account does not exist", bsException.getMessage());
        }
    }

    @And("get merchant")
    public void getMerchant() {
        try {
            // Create
            User mer = new User();
            mer.setCprNumber("m-cpr");
            mer.setFirstName("m-fn");
            mer.setLastName("m-ln");
            String  id = merchantService.register(mer.getFirstName() + " " + mer.getLastName(), mer.getCprNumber(), "merchantAccountIdentifier");

            // Fetch
            Merchant m = merchantService.get(id);
            Assert.assertEquals(m.getCpr(), mer.getCprNumber());

            // Delete
            merchantService.delete(id);
            Assert.assertNull(merchantService.get(id));
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e.getMessage());
        }
    }
}
