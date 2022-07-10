package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.InterventionDto;

public interface InterventionService extends GenericService<InterventionDto> {

	List<InterventionDto> getAllByFormationId(long idFromation);
	
	List<InterventionDto> getAllByPromotionId(long idPromotion);
	
	List<InterventionDto> getAllByFormateurId(long idUtilisateur) throws Exception;
	
	List<InterventionDto> getAll(int page, int max, String search);
}
