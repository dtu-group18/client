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
	 * @param costumerId
	 * @param name
	 * @param cpr
	 * @param bankAccount
	 * @return boolean
	 */
	public boolean register(String costumerId, String name, String cpr, String bankAccount) {
		// Register customer
		Response response  = target.path(costumerId).path(name).path(cpr).path(bankAccount)
				.request()
				.post(null);

		return response.getStatus() == Response.Status.CREATED.getStatusCode();
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
