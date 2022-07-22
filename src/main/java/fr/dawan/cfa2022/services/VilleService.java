package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.VilleDto;

public interface VilleService extends GenericService<VilleDto> {

	int updateFromDg2() throws Exception;
	
	List<VilleDto> getAll(int page, int max, String search);
}
