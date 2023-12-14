package api.endpoints;

import api.payloads.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

//for CRUD operations

public class StudentEndpoint {
	
	static Routes route = new Routes();
	
	
	public static Response createStudent(Student payload){
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.getRoutes().get("post_url"));
		return response;
	}
	
	public static Response readStudent(int studentId) {
		Response response = given().pathParam("studentId", studentId)
				.when().get(Routes.get_url);
		return response;
	}
	
	public static Response readStudentByName(String name) {
		Response response = given()
				.queryParam("studentName", name)
				.when().get(Routes.getRoutes().get("read_url"));
		return response;
	}
	
	public static Response updateStudent(String name, Student payload){
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.queryParam("studentName", name)
			.body(payload)
		.when()
			.put(Routes.getRoutes().get("update_url"));
		return response;
	}
	
	public static Response deleteStudent(int studentId) {
		Response response = given().pathParam("studentId", studentId)
				.when().delete(Routes.delete_url);
		return response;
	}
}
