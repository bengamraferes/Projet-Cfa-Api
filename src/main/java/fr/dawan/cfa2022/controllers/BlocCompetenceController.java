package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.BlocCompetencesDto;
import fr.dawan.cfa2022.services.BlocCompetencesService;

@RestController
@RequestMapping("/blocsCompetence")
public class BlocCompetenceController extends GenericController<BlocCompetencesDto> {
	
	@Autowired
	public BlocCompetenceController(BlocCompetencesService service) {
		super(service);
	}
	@GetMapping(value={"/titresProfessionnel/{id}" ,"/titresProfessionnel/{id}/{search}" },produces = "application/json")
	public List<BlocCompetencesDto> getAllByTitrePro(@PathVariable("id") long id, @PathVariable("search") Optional<String> search ) {
		if (search.isPresent()) {
			return ((BlocCompetencesService) service).getAllByTitreProId(id,search.get());
		}
		return ((BlocCompetencesService) service).getAllByTitreProId(id,"");
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<BlocCompetencesDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((BlocCompetencesService) service).getAll(page-1, max, search.get());
		else
			return ((BlocCompetencesService) service).getAll(page-1, max, "");
	}
}
