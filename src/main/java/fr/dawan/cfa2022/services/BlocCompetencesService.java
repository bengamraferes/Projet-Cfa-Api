package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.BlocCompetencesDto;

public interface BlocCompetencesService extends GenericService<BlocCompetencesDto>  {
	
	List<BlocCompetencesDto> getAllByTitreProId(long id);
	
	List<BlocCompetencesDto> getAll(int page, int max, String search);

}
