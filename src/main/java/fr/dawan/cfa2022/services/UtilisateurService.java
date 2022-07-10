package fr.dawan.cfa2022.services;


import java.util.List;

import fr.dawan.cfa2022.dto.ChangePwdDto;
import fr.dawan.cfa2022.dto.LoginDto;
import fr.dawan.cfa2022.dto.LoginResponseDto;
import fr.dawan.cfa2022.dto.UtilisateurDto;

public interface UtilisateurService extends GenericService<UtilisateurDto> {
	List<UtilisateurDto> getAll();

	List<UtilisateurDto> getAll(int page, int max, String search);
	
	LoginResponseDto checkLogin(LoginDto loginDto) throws Exception;

	UtilisateurDto findByEmail(String email) throws Exception;

	boolean resetPassword(ChangePwdDto changePwdObj) throws Exception;

}
