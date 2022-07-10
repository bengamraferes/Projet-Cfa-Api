package fr.dawan.cfa2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.NiveauDto;
import fr.dawan.cfa2022.services.NiveauService;

@RestController
@RequestMapping("/niveaux")
public class NiveauController extends GenericController<NiveauDto> {
	
	@Autowired
	public NiveauController(NiveauService service) {
		super(service);
	}
	@GetMapping(produces = "application/json")
	public List<NiveauDto> getAll() {
		return ((NiveauService) service).getAll();
	}


}
