package dtu.helloservice;
import org.json.JSONObject;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
		System.out.println(merchantId);
		JSONObject merchant = new JSONObject(target.path(merchantId).request().get());
		System.out.println(merchant);
		return target.path(merchantId).request().get(Merchant.class);
	}

	/**
	 * Validate merchant
	 *
	 * @param merchantId
	 * @return Merchant
	 */
	public boolean validateMerchant(String merchantId) {
		Response response = target.path("validation").path(merchantId).request().get();
		return response.getStatus() == Response.Status.OK.getStatusCode();
	}

	/**
     * Register merchant to dtu simple pay
	 *
	 * @param merchantId
     * @param name
     * @param cpr
     * @param bankAccount
     * @return boolean
	 */
	public boolean register(String merchantId, String name, String cpr, String bankAccount) {
		// Register merchant
		Response response  = target.path(merchantId).path(name).path(cpr).path(bankAccount)
				.request()
				.post(null);

		return response.getStatus() == Response.Status.CREATED.getStatusCode();
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
