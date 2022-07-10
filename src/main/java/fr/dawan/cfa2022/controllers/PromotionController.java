package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.PromotionDto;
import fr.dawan.cfa2022.services.PromotionService;

@RestController
@RequestMapping("/promotions")
public class PromotionController extends GenericController<PromotionDto> {
	
	@Autowired
	public PromotionController(PromotionService service) {
		super(service);
	}
	@GetMapping(produces = "application/json")
	public List<PromotionDto> getAll( ) {
		return ((PromotionService) service).getAll();
	}
	@GetMapping(value="/ville/{id}",produces = "application/json")
	public List<PromotionDto> getAllByVilleId(@PathVariable("id") long id ) {
		return ((PromotionService) service).getAllByVilleId(id);
	}
	@GetMapping(value="/titresProfessionnel/{id}",produces = "application/json")
	public List<PromotionDto> getAllByTitreProId(@PathVariable("id") long id ) {
		return ((PromotionService) service).getAllByTitreProId(id);
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<PromotionDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((PromotionService) service).getAll(page-1, max, search.get());
		else
			return ((PromotionService) service).getAll(page-1, max, "");
	}
}
