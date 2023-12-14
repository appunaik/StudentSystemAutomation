package api.endpoints;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
/*
 * Swagger URI - https://petstore.swagger.io
 * Create User(Post) : https://petstore.swagger.io/v2/user
 * Get User(Get) : https://petstore.swagger.io/v2/user/{username}
 * Update User(Put) : https://petstore.swagger.io/v2/user/{username}
 * Delete User(Delete) : https://petstore.swagger.io/v2/user/{username}
 */
import java.util.stream.Stream;

public class Routes {
	
 

	public static final String base_url="https://petstore.swagger.io/v2";
	public static String post_url="http://localhost:8082/api/students/";
	public static String update_url="http://localhost:8082/api/students/";
	public static String  delete_url="http://localhost:8082/api/students/{studentId}";
	public static String get_url="http://localhost:8082/api/students/{studentId}"; 
	
	static Map<String, String> routesPool = new HashMap<String,String>();
	
	static {
		try {
			FileReader fr = new FileReader("src/test/resources/routes.properties");
		    BufferedReader br = new BufferedReader(fr);
		    Stream<String> lines = br.lines();
		    routesPool =lines.map(s -> s.split("=", 2))
		    		.collect(Collectors.toMap(a -> a[0], a -> a.length>1? a[1]: ""));
		    System.out.println(" sfs " + routesPool.get("post_url"));
		    System.out.println(" routes " + routesPool);
		   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.toString();
		}
	}
	static ResourceBundle getURL() {
		ResourceBundle res = ResourceBundle.getBundle("routes");
		return res;
	}
	
	public static void main(String[] arg) {
//		if(routesPool.size()==0) {
//			try {
				System.out.println(" url " + getURL().getString("post_url"));
				
//				FileReader fr = new FileReader("src/test/resources/routes.properties");
//			    BufferedReader br = new BufferedReader(fr);
//			    Stream<String> lines = br.lines();	
//			    routesPool =lines.map(s -> s.split("=", 2))
//			    		.collect(Collectors.toMap(a -> a[0], a -> a.length>1? a[1]: ""));

			   
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.toString();
//			}
//		}
	}
	
	public static Map<String, String> getRoutes(){
		if(routesPool.size()==0) {
			try {
				FileReader fr = new FileReader("src/test/resources/routes.properties");
			    BufferedReader br = new BufferedReader(fr);
			    Stream<String> lines = br.lines();
			    routesPool =lines.map(s -> s.split("=", 2))
			    		.collect(Collectors.toMap(a -> a[0], a -> a.length>1? a[1]: ""));			   
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.toString();
			}
		}
		return routesPool;
	}
	
	
	public void setRoutes(Map<String,String> map) {
		routesPool=map;
	}
}
