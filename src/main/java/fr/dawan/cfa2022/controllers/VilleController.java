package fr.dawan.cfa2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.VilleDto;
import fr.dawan.cfa2022.services.VilleService;

@RestController
@RequestMapping("/villes")
public class VilleController extends GenericController<VilleDto> {
	
	@Autowired
	public VilleController(VilleService service) {
		super(service);
	}
	@GetMapping(value="/update-from-dg2", produces = "application/json")
	public CountDto updateFromDG2() throws Exception {
		 int nb =((VilleService) service).updateFromDg2();
		 CountDto result = new CountDto();
		 result.setNb(nb);
		 return result;
	}
//	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
//	public List<EpreuveDto> getAllByPage(
//							@PathVariable("page") int page, 
//							@PathVariable("size") int max, 
//							@PathVariable(value="search", required = false) Optional<String> search){
//		if(search.isPresent())
//			return ((EpreuveService) service).getAll(page-1, max, search.get());
//		else
//			return ((EpreuveService) service).getAll(page-1, max, "");
//	}
}
