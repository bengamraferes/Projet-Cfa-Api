 package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.TitreProfessionnelDto;


public interface TitreProfessionnelService extends GenericService<TitreProfessionnelDto>  {

	List<TitreProfessionnelDto> getAll();

	List<TitreProfessionnelDto> getAll(int page, int max, String search);
	
	TitreProfessionnelDto getTitreProfessionnelByPromotionId( long promotionId );
}
