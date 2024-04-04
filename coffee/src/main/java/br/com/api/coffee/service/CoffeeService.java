package br.com.api.coffee.service;

import java.net.URI;

import br.com.api.coffee.controller.CoffeeResource;
import br.com.api.coffee.model.Coffee;
import br.com.api.coffee.repository.CoffeeRepository;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.Response.ResponseBuilder;

public class CoffeeService {

	public static Coffee save(Coffee cafe) {
		return cafe = CoffeeRepository.save(cafe);
	}
	
	public static Coffee update(Long id, Coffee cafe) {
		Coffee source = CoffeeService.findById(id);
		Coffee target = null;
		
		if(source == null || source.getId() != cafe.getId()) {
			return CoffeeService.save(cafe);
		}
		
		target = CoffeeRepository.update(cafe);
		return target;
		
	}
	
	public static Coffee findById(Long id) {
		return CoffeeRepository.findById(id);
	}
	
	
	public static boolean exists(Long id) {
		return CoffeeRepository.findById(id) != null ? true : false;
	}
	
	public static boolean delete(Long id) {
		if(exists(id)) {
			return CoffeeRepository.delete(id);
		}else {
			return false;
		}
		
	}
}
