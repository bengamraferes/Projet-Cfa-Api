package fr.dawan.cfa2022.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.services.GenericService;

public abstract class GenericController<TDto> {


	protected  final GenericService<TDto> service;
	
	public GenericController(GenericService<TDto> service) {
		super();
		this.service = service;
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	public TDto findById(@PathVariable("id") long id){
		return service.getById(id);
	}
	@DeleteMapping(value = "/{id}", produces = "text/plain")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") long id) {
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("suppression effectuée");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("suppression non réalisée");
		}

	}
	@PostMapping(consumes = "application/json",produces = "application/json")
	ResponseEntity<TDto> save( @RequestBody TDto tDto) throws Exception {
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.saveOrUpdate(tDto));
	}
	@PutMapping(consumes = "application/json",produces = "application/json")
	TDto update( @RequestBody TDto tDto) throws Exception {
		return service.saveOrUpdate(tDto);
	}
	@GetMapping(value = {"/count/{search}","/count"}, produces = "application/json")
	public CountDto count(@PathVariable(value = "search" ,required = false) Optional<String> search) {
		if (search.isPresent())
			return service.count(search.get());
		return service.count("");
	}
}
