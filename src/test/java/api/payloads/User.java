package api.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class User {

	int id;
	String userName;
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;
	int userStatus=0;

}
