package fr.dawan.cfa2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.CountAvgDto;
import fr.dawan.cfa2022.dto.EpreuveDto;
import fr.dawan.cfa2022.dto.EvaluationDto;
import fr.dawan.cfa2022.services.EvaluationService;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController extends GenericController<EvaluationDto> {
	
	@Autowired
	public EvaluationController(EvaluationService service) {
		super(service);
	}
	@GetMapping(value="/etudiant/{id}",produces = "application/json")
	public List<EvaluationDto> getAllByEtudiantId(@PathVariable("id") long id ) {
		return ((EvaluationService) service).findAllByEtudiantId(id);
	}
	@GetMapping(value="/promotion/{id}",produces = "application/json")
	public List<EvaluationDto> findAllByPromotionId(@PathVariable("id") long id ) {
		return ((EvaluationService) service).findAllByPromotionId(id);
	}
	@GetMapping(value = "byEtudiantAndEpreuve/{idEtudiant}/{idEpreuve}")
	public EvaluationDto getByEtudiantAndEpreuve(@PathVariable("idEtudiant") long idEtudiant,@PathVariable("idEpreuve") long idEpreuve) {
		return ((EvaluationService) service).findAllByEtudiantIdAndEpreuveId(idEtudiant, idEpreuve);
		
	}
	@GetMapping(value="/avg/etudiantByPromotion/{idEtudiant}/{idPromotion}",produces = "application/json")
	public CountAvgDto getAvgByEtudiantIdAndPromotionId(@PathVariable("idEtudiant") long idEtudiant, @PathVariable("idPromotion") long idPromotion ) {
		return ((EvaluationService) service).getAvgByEtudiantIdAndPromotionId(idEtudiant, idPromotion);
	}
	
	@GetMapping(value="/avg/byPromotion/{idPromotion}",produces = "application/json")
	public CountAvgDto  getAvgByPromotionId(@PathVariable("idPromotion") long idPromotion ) {
		return ((EvaluationService) service).getAvgByPromotionId(idPromotion);
	}
	@GetMapping(value="/avg/blocComptetecesByPromotion/{idBc}/{idPromotion}",produces = "application/json")
	public CountAvgDto getAvgByBlocCompetencesIdAndPromotionId(@PathVariable("idBc") long idBc, @PathVariable("idPromotion") long idPromotion ) {
		return ((EvaluationService) service).getAvgByPromotionIdAndBlocDeCompetences(idBc, idPromotion);
	}
	@GetMapping(value="/avg/EtudiantByblocCompteteces/{etudiantId}/{blocCompetencesId}",produces = "application/json")
	public CountAvgDto getAvgByEtudiantIdAndBlocCompId(@PathVariable("etudiantId")long etudiantId, @PathVariable("blocCompetencesId") long blocCompetencesId ) {
		return ((EvaluationService) service).getAvgByEtudiantIdAndBlocCompId(etudiantId, blocCompetencesId);
	}
	
}
