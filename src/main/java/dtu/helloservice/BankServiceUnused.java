package dtu.helloservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class BankServiceUnused {
    //building the client and the target
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8081/");


}
