package com.in28minutes.rest.webservices.restfulwebservices.user;

//WebMvcLinkBuilder에 있는 메소드에 대한 정적 임포트를 수행
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserResource {

	private final UserDaoService service;

	// useer데이터 전체 죄회 get사용
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// user데이터에서 원하는 정보만 검색
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveSearchUsers(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("없는 id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		// WebMvcLinkBuilder = 스프링 MVC컨트롤러를 가르키는 링크 인스턴스의 구축을 용이하게 하는 빌더
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());// 이 클래스의 retrieveAllUsers()의 url을 가져옴
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	// user데이터에서 원하는 정보만 검색
	@GetMapping(path = "/search/{id}")
	public void retrieveSearchData(@PathVariable int id) {
		throw new StrangeDataSearchException();
	}
	
	// user데이터삭제
	@DeleteMapping(path = "/users/{id}")
	public void retrieveDeleteUsers(@PathVariable int id) {
		service.deleteById(id);
	}

	// user 데이터 추가
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()// 요청의 url을 가져옴 여기서는 /users가 옴
				.path("/{id}")// 패스 매개변수 붙이기
				.buildAndExpand(savedUser.getId())// 페스 매개변수에 값넣기 
				.toUri();
		System.out.println(location);
		System.out.println(savedUser);
		System.out.println(ResponseEntity.created(location).build());
		return ResponseEntity.created(location).build();
	}
}
