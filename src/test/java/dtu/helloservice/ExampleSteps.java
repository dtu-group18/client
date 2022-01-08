package dtu.helloservice;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExampleSteps {

	String result;
	Person p;

	PaymentService service = new PaymentService();

	@When("I call the hello service")
	public void iCallTheHelloService(){result = service.hello();}

	@Then("I get the answer {string}")
	public void iGetTheAnswer(String str){assertEquals(str , result);}

	@When("I call the hello service asking for a person")
	public void iCallHelloServicePerson(){p = service.person();}

	@Then("I get a person with name {string} and address {string}")
	public void iGetAPersonWithNameAndAddress(String name, String person){
		assertEquals(name, p.name);
		assertEquals(person, p.address);
	}

}


