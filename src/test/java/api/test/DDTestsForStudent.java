package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StudentEndpoint;
import api.payloads.Student;
import api.utilities.DataProvidersForStudent;
import io.restassured.response.Response;

public class DDTestsForStudent {
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProvidersForStudent.class,enabled = true)
	public void testPostStudent(String userId,String username, String fName, String lName,
				String email, String pwd, String phone) {
		Student stu = new Student();
		stu.setEmail(email);
		stu.setFirstName(fName);
		stu.setLastName(lName);
		stu.setUserName(username);
		stu.setPassword(pwd);
		stu.setPhone(phone);
		
		Response response = StudentEndpoint.createStudent(stu);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority=2, dataProvider="StudentNames", dataProviderClass=DataProvidersForStudent.class,enabled = true)
	public void testGetStudentByName(String name){
		//Response response = StudentEndpoint.deleteStudent(Id);
		Response response = StudentEndpoint.readStudentByName(name);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
