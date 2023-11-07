package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties({"field1","field2"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	
	//@JsonIgnore
	private String field2;
	private String field3;
}
