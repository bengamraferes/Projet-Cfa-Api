package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.CountAvgDto;
import fr.dawan.cfa2022.dto.EvaluationDto;

public interface EvaluationService extends GenericService<EvaluationDto> {
	
	List<EvaluationDto> findAllByEtudiantId(long id);
	
	CountAvgDto getAvgByEtudiantIdAndPromotionId(long etudiantId, long promotionId );
	
	CountAvgDto getAvgByPromotionId(long promotionId);
	
	CountAvgDto getAvgByPromotionIdAndBlocDeCompetences(long bcId, long promotionId);
	
	CountAvgDto getAvgByEtudiantIdAndBlocCompId( long etudiantId,long blocCompetencesId);
	
	List<EvaluationDto> findAllByPromotionId(long promotionId);
	
	EvaluationDto findAllByEtudiantIdAndEpreuveId(long idEtudiant, long idEpreuve);
}
