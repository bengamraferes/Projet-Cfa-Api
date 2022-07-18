package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.EtudiantDto;

public interface EtudiantService  extends GenericService<EtudiantDto>{

	List<EtudiantDto> getAllByPromotionId(long id);
	List<EtudiantDto> getAll(int page, int max, String search);
	
}
