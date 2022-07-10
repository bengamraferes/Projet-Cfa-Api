package fr.dawan.cfa2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.EtudiantDto;
import fr.dawan.cfa2022.services.EtudiantService;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController extends GenericController<EtudiantDto> {
	
	@Autowired
	public EtudiantController(EtudiantService service) {
		super(service);
	}
	@GetMapping(value="/promotion/{id}",produces = "application/json")
	public List<EtudiantDto> getAllByTitrePro(@PathVariable("id") long id ) {
		return ((EtudiantService) service).getAllByPromotionId(id);
	}
}
