package fr.dawan.cfa2022.services;

import fr.dawan.cfa2022.dto.UtilisateurDto;

public interface EmailService {

	void sendEmailForResetPwd(UtilisateurDto uDto)  throws Exception;

}
