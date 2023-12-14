package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StudentEndpoint;
import api.payloads.Student;
import io.restassured.response.Response;

public class StudentTests {

	Faker faker;
	Student studentpayload;
	Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		studentpayload=new Student();
		
		studentpayload.setId(faker.idNumber().hashCode());
		studentpayload.setEmail(faker.internet().safeEmailAddress());
		studentpayload.setFirstName(faker.name().firstName());
		studentpayload.setLastName(faker.name().lastName());
		studentpayload.setUserName(faker.name().username());
		studentpayload.setPassword(faker.internet().password(5,10));
		studentpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostStudent() {
		logger.debug("***********CreatingStudent***********");
		Response response = StudentEndpoint.createStudent(studentpayload);
		response.then().log().all();
		studentpayload=response.getBody().as(Student.class);
		System.out.println(" studet is " + studentpayload);
		Assert.assertEquals(response.getStatusCode(), 201);
		logger.debug("***********User created***********");
	}
	
	@Test(priority=2)
	public void testGetStudentByName() {
		logger.debug("***********Get Student***********");
		Response response = StudentEndpoint.readStudent(this.studentpayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		logger.debug("***********Get Student Done***********");
	}
		
	
	@Test(priority=3)
	public void testUpdateStudentByName() {
		logger.debug("***********Update Student***********");
		System.out.println("Existing fName " + studentpayload.getFirstName() + " " + studentpayload.getEmail());
		this.studentpayload.setFirstName(faker.name().firstName());
		this.studentpayload.setEmail(faker.internet().safeEmailAddress());
		System.out.println("New fName " + this.studentpayload.getFirstName() + " " + this.studentpayload.getEmail());

		System.out.println("ID is " + studentpayload.getId());
		Response response = StudentEndpoint.updateStudent(this.studentpayload.getUserName(), this.studentpayload);
		response.then().log().body();
		Assert.assertEquals(response.statusCode(),200);
		
		Response responseAfterUpdate = StudentEndpoint.readStudentByName(this.studentpayload.getUserName());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.debug("***********Update Student done***********");
	}
	
	@Test(priority=4)
	public void testDeleteStudentByName() {
		logger.debug("***********Delete Student***********");
		Response response =StudentEndpoint.deleteStudent(this.studentpayload.getId());
		Assert.assertEquals(response.statusCode(),200);
		logger.debug("***********Delete Student done***********");
	}
}
