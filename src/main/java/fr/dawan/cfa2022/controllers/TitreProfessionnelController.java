package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.TitreProfessionnelDto;
import fr.dawan.cfa2022.services.TitreProfessionnelService;

@RestController
@RequestMapping("/titresProfessionnel")
public class TitreProfessionnelController extends GenericController<TitreProfessionnelDto> {
	
	@Autowired
	public TitreProfessionnelController(TitreProfessionnelService service) {
		super(service);
	}
	@GetMapping(produces = "application/json")
	public List<TitreProfessionnelDto> getAll() {
		return ((TitreProfessionnelService) service).getAll();
	}
	@GetMapping(value = "/promotion/{id}", produces = "application/json")
	public TitreProfessionnelDto getByPromotionId(@PathVariable("id") long id ) {
		return ((TitreProfessionnelService) service).getTitreProfessionnelByPromotionId(id);
	}
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<TitreProfessionnelDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return ((TitreProfessionnelService) service).getAll(page-1, max, search.get());
		else
			return ((TitreProfessionnelService) service).getAll(page-1, max, "");
	}
	@GetMapping(value="/dg2", produces = "application/json")
	public CountDto updateFromDG2() throws Exception {
		 int nb =((TitreProfessionnelService) service).importFromDG2();
		 CountDto result = new CountDto();
		 result.setNb(nb);
		 return result;
	}
}
