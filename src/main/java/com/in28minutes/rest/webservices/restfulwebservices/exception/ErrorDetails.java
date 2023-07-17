package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class ErrorDetails {
	
	// 에외발생 기간 정보
	private LocalDate timestamp;
	// 메세지
	private String message;
	// 에외 상세
	private String details;
	// 상태 코드
	int status;
	public ErrorDetails(LocalDate timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	
}
