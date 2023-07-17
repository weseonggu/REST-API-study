package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.StrangeDataSearchException;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class) // 어떤 예외를 여기서 처리할건지 정의(여기서는 모든 에외를 받아들임)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request,HttpStatusCode status) throws Exception {
		// 여기에 어떤종류의 예외처리를 항건지 정의
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false), status.value());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class) // 어떤 예외를 여기서 처리할건지 정의(여기서는 UserNotFoundException 에외를 받아들임)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		// 여기에 어떤종류의 예외처리를 항건지 정의
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false),HttpStatus.NOT_FOUND.value());
		System.out.println("UserNotFoundException실행");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StrangeDataSearchException.class) // 어떤 예외를 여기서 처리할건지 정의
	public final ResponseEntity<ErrorDetails> handleStrangeDataSearchException(Exception ex, WebRequest request)
			throws Exception {
		// 여기에 어떤종류의 예외처리를 항건지 정의
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// 유효성 검증 예외 응답 커스텀
	@Override// ResponseEntityExceptionHandler 클레스에 이미정의되어 있는걸 사용함 이 클래스를 상속이 없다면 MethodArgumentNotValidException예외에 대한 예외처리를 정의 해야함 위처럼
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
				"Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(),
				request.getDescription(false),
				status.value());

		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
