package fr.dawan.cfa2022.services;

import java.util.List;


import fr.dawan.cfa2022.dto.CompetenceDto;

public interface CompetenceService extends GenericService<CompetenceDto>  {
    List<CompetenceDto> getAllByTitreProfessionnelId(long id);
    
	
	List<CompetenceDto> getAll(int page, int max, String search);

	List<CompetenceDto> getAllByBlocComptenceId(long id, String search);
}
