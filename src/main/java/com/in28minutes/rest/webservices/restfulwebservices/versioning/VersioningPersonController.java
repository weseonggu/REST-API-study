package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson(){
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	@GetMapping(path="/person", params = "version=1")// 이러면 파라메터가 version=1일 경우에만 이 메소드가 실행된다
	public PersonV1 getFirstVersionOfPersonRequestParameter(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", params = "version=2")// 이러면 파라메터가 version=2일 경우에만 이 메소드가 실행된다
	public PersonV2 getSecondVersionOfPersonRequestParameter(){
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	@GetMapping(path="/person/header", headers = "X-API-VERSION=1")// 이러면 헤더가 X-API-VERSION아고 그 값이1일 경우에만 이 메소드가 실행된다
	public PersonV1 getFirstVersionOfPersonRequestHeader(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/header", headers = "X-API-VERSION=2")// 이러면 헤더가 X-API-VERSION아고 그 값이1일 경우에만 이 메소드가 실행된다
	public PersonV2 getSecondVersionOfPersonRequestHeader(){
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {// 콘텐츠 협상 방법중 accept헤더를 사용
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {// 콘텐츠 협상 방법중 accept헤더를 사용
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
