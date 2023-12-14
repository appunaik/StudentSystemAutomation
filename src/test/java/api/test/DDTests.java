package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

@Test(enabled=false)
public class DDTests {
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class,enabled=false)
	public void testPostUser(String userId,String username, String fName, String lName,
				String email, String pwd, String phone) {
		User userpayload = new User();
		userpayload.setEmail(email);
		userpayload.setFirstName(fName);
		userpayload.setLastName(lName);
		userpayload.setUserName(username);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phone);
		
		Response response = UserEndpoint.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class,enabled=false)
	public void testDeleteUserByName(String userName){
		Response response = UserEndpoint.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
