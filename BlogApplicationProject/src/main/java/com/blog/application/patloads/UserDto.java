package com.blog.application.patloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
// Data transfer object
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4, max = 50 , message = "Username must be min fo 4 char")
	private String name;
	@Email(message="Email address must be valid")
	private String email;
	@NotEmpty(message= "Use special character to make password strong")
	@Size(min= 5 , max= 10, message="password must be min of 3 char and max of 10 chars")
	private String password;
	@NotNull
	private String about;

	
}
