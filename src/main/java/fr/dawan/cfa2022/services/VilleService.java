package fr.dawan.cfa2022.services;

import fr.dawan.cfa2022.dto.VilleDto;

public interface VilleService extends GenericService<VilleDto> {

	int updateFromDg2() throws Exception;
}
