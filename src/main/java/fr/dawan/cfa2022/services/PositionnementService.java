package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.PositionnementDto;

public interface PositionnementService extends GenericService<PositionnementDto> {

	List<PositionnementDto> getAllByInterventionId(long idIntervention);
	
	List<PositionnementDto> getAllByPromotionId(long idPromotion);
}
