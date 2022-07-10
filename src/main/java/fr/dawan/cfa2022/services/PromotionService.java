package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.PromotionDto;

public interface PromotionService extends GenericService<PromotionDto> {
	List<PromotionDto> getAll();
	
	List<PromotionDto> getAll(int page, int max, String search);
	
	List<PromotionDto> getAllByTitreProId(long id);
	
	List<PromotionDto> getAllByVilleId(long id);
	

}
