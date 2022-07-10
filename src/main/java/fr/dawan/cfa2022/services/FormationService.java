package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.FormationDto;

public interface FormationService extends GenericService<FormationDto>{

	List<FormationDto> getAll();
	
	List<FormationDto> getAll(int page, int max, String search);
	
}
