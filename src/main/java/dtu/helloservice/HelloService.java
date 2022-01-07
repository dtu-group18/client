package dtu.helloservice;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class HelloService {

	//building the client and the target
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:8081/");

	/**
	 * Method that calls the REST api requesting the hello resource
	 * @return the hello resource
	 */
	public String hello(){
		String str = target.path("hello").request().get(String.class);
		return str;
	}

	/**
	 * Method that calls the REST api requesting the person resource
	 * @return the person resource
	 */
	public Person person(){
		Person p = target.path("hello").path("person").request().accept("application/json").get(Person.class);
		return p;
	}
}
