package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "user_details")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message = "두글자 이상 적으시오")
	//@JsonProperty("user_name")
	private String name;
	@Past(message = "Birth Date should be in the past")
	//@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")// 한 유저가 쓴 글 목록들 [일대다]
	// mappedBy속성 섫정 필수
	@JsonManagedReference
	private List<Post> posts;

}
