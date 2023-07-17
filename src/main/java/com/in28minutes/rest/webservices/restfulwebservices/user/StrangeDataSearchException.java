package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StrangeDataSearchException extends RuntimeException{
	public StrangeDataSearchException() {
		super("이상한 데이터 검색");
	}
}
