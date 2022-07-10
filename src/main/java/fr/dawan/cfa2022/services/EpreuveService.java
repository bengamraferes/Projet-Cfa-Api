package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.EpreuveDto;
import fr.dawan.cfa2022.entities.Epreuve.Type;

public interface EpreuveService extends GenericService<EpreuveDto> {
	
	List<EpreuveDto> findAllByblocCompetencesId(long id);
	
	List<EpreuveDto> findAllByType(Type type);
	
	List<EpreuveDto> getAll(int page, int max, String search);
	
}
