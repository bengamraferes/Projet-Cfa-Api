package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.UtilisateurDto;
import fr.dawan.cfa2022.entities.Utilisateur.Role;
import fr.dawan.cfa2022.services.UtilisateurService;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController extends GenericController<UtilisateurDto> {
	
	@Autowired
	public UtilisateurController(UtilisateurService service) {
		super(service);
	}
	@GetMapping(produces = "application/json")
	public List<UtilisateurDto> getAll() {
		return ((UtilisateurService) service).getAll();
	}
	
	@GetMapping(value = "/role/{role}", produces = "application/json")
	public List<UtilisateurDto> getByRole(@PathVariable("role") String roleS) {
		Role role =  Role.valueOf(roleS);
		return ((UtilisateurService) service).getByRole(role);
	}
	
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<UtilisateurDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((UtilisateurService) service).getAll(page-1, max, search.get());
		else
			return ((UtilisateurService) service).getAll(page-1, max, "");
	}
}
