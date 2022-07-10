package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.FormationDto;
import fr.dawan.cfa2022.services.FormationService;

@RestController
@RequestMapping("/formations")
public class FormationController extends GenericController<FormationDto> {
	
	@Autowired
	public FormationController(FormationService service) {
		super(service);
	}
	@GetMapping(produces = "application/json")
	public List<FormationDto> getAll( ) {
		return ((FormationService) service).getAll();
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<FormationDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((FormationService) service).getAll(page-1, max, search.get());
		else
			return ((FormationService) service).getAll(page-1, max, "");
	}
}
