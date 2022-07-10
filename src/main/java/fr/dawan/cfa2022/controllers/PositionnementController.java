package fr.dawan.cfa2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.PositionnementDto;
import fr.dawan.cfa2022.services.PositionnementService;

@RestController
@RequestMapping("/positionnements")
public class PositionnementController extends GenericController<PositionnementDto> {
	
	@Autowired
	public PositionnementController(PositionnementService service) {
		super(service);
	}
	@GetMapping(value="/intervention/{id}",produces = "application/json")
	public List<PositionnementDto> getAllByInterventionId(@PathVariable("id") long id ) {
		return ((PositionnementService) service).getAllByInterventionId(id);
	}
	@GetMapping(value="/promotion/{id}",produces = "application/json")
	public List<PositionnementDto> getAllByPromotionId(@PathVariable("id") long id ) {
		return ((PositionnementService) service).getAllByPromotionId(id);
	}

}
