package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.NiveauDto;

public interface NiveauService  extends GenericService<NiveauDto>{

	List<NiveauDto> getAll();
	
}
