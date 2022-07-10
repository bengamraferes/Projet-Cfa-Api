package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.CompetenceDto;
import fr.dawan.cfa2022.services.CompetenceService;

@RestController
@RequestMapping("/competences")
public class CompetenceController extends GenericController<CompetenceDto> {
	
	@Autowired
	public CompetenceController(CompetenceService service) {
		super(service);
	}
	@GetMapping(value="/titresProfessionnel/{id}",produces = "application/json")
	public List<CompetenceDto> getAllByTitrePro(@PathVariable("id") long id ) {
		return ((CompetenceService) service).getAllByTitreProfessionnelId(id);
	}
	@GetMapping(value="/blocCompetence/{id}",produces = "application/json")
	public List<CompetenceDto> getAllByBlocComptenceId(@PathVariable("id") long id ) {
		return ((CompetenceService) service).getAllByBlocComptenceId(id);
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<CompetenceDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((CompetenceService) service).getAll(page-1, max, search.get());
		else
			return ((CompetenceService) service).getAll(page-1, max, "");
	}
}
