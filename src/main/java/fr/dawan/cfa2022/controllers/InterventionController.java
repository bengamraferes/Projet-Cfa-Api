package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.InterventionDto;
import fr.dawan.cfa2022.services.InterventionService;

@RestController
@RequestMapping("/interventions")
public class InterventionController extends GenericController<InterventionDto> {
	
	@Autowired
	public InterventionController(InterventionService service) {
		super(service);
	}
	@GetMapping(value="/formateur/{id}",produces = "application/json")
	public List<InterventionDto> getAllByFormateurId(@PathVariable("id") long id ) throws Exception {
		return ((InterventionService) service).getAllByFormateurId(id);
	}
	@GetMapping(value="/formation/{id}",produces = "application/json")
	public List<InterventionDto> getAllByFormationId(@PathVariable("id") long id ) {
		return ((InterventionService) service).getAllByFormationId(id);
	}
	@GetMapping(value="/promotion/{id}",produces = "application/json")
	public List<InterventionDto> getAllByPromotionId(@PathVariable("id") long id )  {
		return ((InterventionService) service).getAllByPromotionId(id);
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<InterventionDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((InterventionService) service).getAll(page-1, max, search.get());
		else
			return ((InterventionService) service).getAll(page-1, max, "");
	}
}
