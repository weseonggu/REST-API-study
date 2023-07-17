package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private Integer id;
	@Size(min=2, message = "두글자 이상 적으시오")
	private String name;
	@Past(message = "Birth Date should be in the past")
	private LocalDate birthDate;

}
