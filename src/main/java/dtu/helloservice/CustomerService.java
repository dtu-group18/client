package dtu.helloservice;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CustomerService {

	//building the client and the target
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:8080/customer");

	/**
	 * Get customer
	 *
	 * @param customerId
	 * @return Customer
	 */
	public Customer get(String customerId) throws NotFoundException {
		// Get customer
//		return target.path(customerId).request().get(Customer.class);
		return target.path(customerId).request().get(Customer.class);
	}

	/**
	 * Register customer to dtu simple pay
	 *
	 * @param name
	 * @param cpr
	 * @param bankAccount
	 * @return boolean
	 */
	public String register(String name, String cpr, String bankAccount) {
		// Register customer
		Customer newCustomer = new Customer();
		newCustomer.setCpr(cpr);
		newCustomer.setBankAccount(bankAccount);
		newCustomer.setName(name);
		Response response  = target.path("registration")
				.request()
				.post(Entity.entity(newCustomer, MediaType.APPLICATION_JSON_TYPE));

		// Get customer id
		String id = response.readEntity(String.class);

		return response.getStatus() == Response.Status.OK.getStatusCode() ? id : null;
	}

	/**
	 * Remove customer registration from dtu simple pay
	 *
	 * @param costumerId
	 * @return boolean
	 */
	public boolean delete(String costumerId) {
		// Remove customer registration
		Response response  = target.path(costumerId)
				.request()
				.delete();

		return response.getStatus() == Response.Status.OK.getStatusCode();
	}
}
