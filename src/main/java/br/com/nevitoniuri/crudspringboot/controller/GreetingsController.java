package br.com.nevitoniuri.crudspringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/retornanome/{name}", method = RequestMethod.GET)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API: " + name + "!";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/olamundo/{name}", method = RequestMethod.GET)
	public String metodoRetornaOlaMundo (@PathVariable String name) {
		return "Ola mundo " + name;
	}
}
