package dtu.helloservice;

//import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MerchantService {
	//building the client and the target
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:8080/merchant");

	/**
	 * Get merchant
	 *
	 * @param merchantId
	 * @return Merchant
	 */
	public Merchant get(String merchantId) {
		// Get merchant
		return target.path(merchantId).request().get(Merchant.class);
	}

	/**
     * Register merchant to dtu simple pay
	 *
     * @param name
     * @param cpr
     * @param bankAccount
     * @return boolean
	 */
	public String register(String name, String cpr, String bankAccount) {
		// Register merchant
		Merchant newMerchant = new Merchant();
		newMerchant.setCpr(cpr);
		newMerchant.setBankAccount(bankAccount);
		newMerchant.setName(name);
		Response response = target.path("registration")
					.request()
					.post(Entity.entity(newMerchant, MediaType.APPLICATION_JSON_TYPE));

		// Get merchant id
		String mid = response.readEntity(String.class);

		return response.getStatus() == Response.Status.OK.getStatusCode() ? mid : null;
	}

	/**
	 * Remove merchant registration from dtu simple pay
	 *
	 * @param merchantId
	 * @return boolean
	 */
	public boolean delete(String merchantId) {
		// Remove customer registration
		Response response  = target.path(merchantId)
				.request()
				.delete();

		return response.getStatus() == Response.Status.OK.getStatusCode();
	}
}
