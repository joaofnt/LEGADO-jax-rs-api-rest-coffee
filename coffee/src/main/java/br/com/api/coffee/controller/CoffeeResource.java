package br.com.api.coffee.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import br.com.api.coffee.model.Coffee;
import br.com.api.coffee.repository.CoffeeRepository;
import br.com.api.coffee.service.CoffeeService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/coffee")
public class CoffeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		
		List<Coffee> retorno = CoffeeRepository.findAll();
		 ResponseBuilder response = Response.ok();
		 response.entity(retorno);
		 return response.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save (@Valid Coffee cafe) {
		
		Coffee resp = CoffeeService.save(cafe);
		final URI coffeeURI = UriBuilder.fromResource(CoffeeResource.class)
				.path("/coffee/{id}")
				.build(resp.getId());
		
		
		ResponseBuilder response = Response.created(coffeeURI);
		response.entity(resp);
		
		return response.build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long cafeId) throws SQLException {
		
		if(CoffeeRepository.delete(cafeId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}else {
			System.out.println("Não foi possivel remover");
			ResponseBuilder response = Response.notModified();
			return response.build();
		}
		
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long cafeId) {
		if(CoffeeService.delete(cafeId)) {
			 ResponseBuilder response = Response.ok();
			 return response.build();
		}else {
			ResponseBuilder response = Response.status(404);
			return response.build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id,@Valid Coffee cafe) {
		Coffee target = CoffeeService.update(id, cafe);
		return Response.ok().build();
	}
}
