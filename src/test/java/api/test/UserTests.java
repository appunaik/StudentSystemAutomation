package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payloads.User;
import io.restassured.response.Response;

@Test(enabled=false)
public class UserTests {

	Faker faker;
	User userpayload;
	Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userpayload=new User();

		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setUserName(faker.name().username());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1,enabled = false)
	public void testPostUser() {
		
		logger.debug("***********CreatingUser***********");
		Response response = UserEndpoint.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********User is created***********");
		
		Assert.assertEquals(false, null);
	}
	
	@Test(priority=2,enabled = false)
	public void testGetUserByName() {
		Response response = UserEndpoint.readUser(this.userpayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
	}
	
	
	
	@Test(priority=3,enabled = false)
	public void testUpdateUserByName() {
		this.userpayload.setFirstName(faker.name().firstName());
		this.userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndpoint.updateUser(this.userpayload.getUserName(), userpayload);
		response.then().log().body();
		Assert.assertEquals(response.statusCode(),200);
		
		Response responseAfterUpdate = UserEndpoint.readUser(this.userpayload.getUserName());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority=4,enabled = false)
	public void testDeleteUserByName() {
		Response response =UserEndpoint.deleteUser(this.userpayload.getUserName());
		Assert.assertEquals(response.statusCode(),200);
	}
}
