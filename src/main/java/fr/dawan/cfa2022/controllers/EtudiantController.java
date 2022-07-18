package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.EtudiantDto;
import fr.dawan.cfa2022.dto.UtilisateurDto;
import fr.dawan.cfa2022.services.EtudiantService;
import fr.dawan.cfa2022.services.UtilisateurService;

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
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<EtudiantDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((EtudiantService) service).getAll(page-1, max, search.get());
		else
			return ((EtudiantService) service).getAll(page-1, max, "");
	}
}
