package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.EpreuveDto;
import fr.dawan.cfa2022.entities.Epreuve.Type;
import fr.dawan.cfa2022.services.EpreuveService;

@RestController
@RequestMapping("/epreuves")
public class EpreuveController extends GenericController<EpreuveDto> {
	
	@Autowired
	public EpreuveController(EpreuveService service) {
		super(service);
	}
	@GetMapping(value="/blocCompetences/{id}",produces = "application/json")
	public List<EpreuveDto> getAllByTitrePro(@PathVariable("id") long id ) {
		return ((EpreuveService) service).findAllByblocCompetencesId(id);
	}
	@GetMapping(value="/type/{type}",produces = "application/json")
	public List<EpreuveDto> getAllByTypr(@PathVariable("type") 	String type ) {
		Type eType = Type.valueOf(type);
		return ((EpreuveService) service).findAllByType(eType);
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<EpreuveDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((EpreuveService) service).getAll(page-1, max, search.get());
		else
			return ((EpreuveService) service).getAll(page-1, max, "");
	}
}
