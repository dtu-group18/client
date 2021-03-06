package dtu.helloservice;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class PaymentService {

	//building the client and the target
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:8080/payment");

	public boolean add(String costumerId, String merchantId, String amount) throws NotFoundException {
		Payment payment = new Payment(costumerId, merchantId, amount);
		Response response  = target.path("add")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN)
				.post(Entity.entity(payment, MediaType.APPLICATION_JSON));
		switch (response.getStatus()) {
			case 200:
			case 201:
				return true;
			case 404:
				throw new NotFoundException(response.getHeaderString("errMsg"));
			default:
				return false;
		}
	}

	public Payment get(String costumerId, String merchantId, String amount) {
		return target.path("test").queryParam("cid", costumerId).queryParam("mid", merchantId).queryParam("amount", amount).request().get(Payment.class);
	}

	public void delete(String costumerId, String merchantId, String amount) {
		target.queryParam("cid", costumerId).queryParam("mid", merchantId).queryParam("amount", amount).request().delete();
	}

	public List<Payment> getPaymentsList() {
		return target.path("list").request().get(new GenericType<List<Payment> >() {});
	}


}
