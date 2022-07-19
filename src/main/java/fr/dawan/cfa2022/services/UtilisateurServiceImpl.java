package fr.dawan.cfa2022.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.ChangePwdDto;
import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.LoginDto;
import fr.dawan.cfa2022.dto.LoginResponseDto;
import fr.dawan.cfa2022.dto.UtilisateurDto;
import fr.dawan.cfa2022.entities.Utilisateur;
import fr.dawan.cfa2022.entities.Utilisateur.Role;
import fr.dawan.cfa2022.repositories.UtilisateurRepository;
import fr.dawan.cfa2022.tools.HashTools;
import fr.dawan.cfa2022.tools.JwtTokenUtil;
import fr.dawan.cfa2022.tools.TokenSaver;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public List<UtilisateurDto> getAll() {
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
		List<UtilisateurDto> utilisateursDto = new ArrayList<UtilisateurDto>();
		for (Utilisateur utilisateur : utilisateurs) {
			utilisateursDto.add(DtoTools.convert(utilisateur, UtilisateurDto.class));
		}
		return utilisateursDto;
	}

	@Override
	public UtilisateurDto getById(long id) {

		Optional<Utilisateur> ut = utilisateurRepository.findById(id);
		if (ut.isPresent()) {
			return DtoTools.convert(ut.get(), UtilisateurDto.class);
		}
		return null;
	}

	@Override
	public UtilisateurDto saveOrUpdate(UtilisateurDto uDto) {
		Utilisateur u = DtoTools.convert(uDto, Utilisateur.class);
		try {
			if (u.getId() == 0) { // insertion
				u.setCreationDate(LocalDateTime.now());
				u.setPassword(HashTools.hashSHA512(u.getPassword()));
			} else { // modif
				UtilisateurDto userInDb = getById(u.getId());
				if (!userInDb.getPassword().contentEquals(u.getPassword())) {
					u.setPassword(HashTools.hashSHA512(u.getPassword()));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		u = utilisateurRepository.saveAndFlush(u);
		return DtoTools.convert(u, UtilisateurDto.class);
	}

	@Override
	public List<UtilisateurDto> getAll(int page, int max, String search) {
		List<Utilisateur> utilisateurs = utilisateurRepository
				.findAllByFirstNameContainingOrLastNameContainingOrEmailContaining(search, search, search,
						PageRequest.of(page, max))
				.get().collect(Collectors.toList());

		List<UtilisateurDto> utilisateursDto = new ArrayList<UtilisateurDto>();
		for (Utilisateur utilisateur : utilisateurs) {
			utilisateursDto.add(DtoTools.convert(utilisateur, UtilisateurDto.class));
		}
		return utilisateursDto;
	}

	@Override
	public void delete(long id) {
		utilisateurRepository.deleteById(id);
	}

	@Override
	public CountDto count(String search) {
		long nb = utilisateurRepository.countByFirstNameContainingOrLastNameContainingOrEmailContaining(search, search,
				search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public LoginResponseDto checkLogin(LoginDto loginDto) throws Exception {
		Utilisateur u = utilisateurRepository.findByEmail(loginDto.getEmail());
		if (u != null && u.getPassword().equals(HashTools.hashSHA512(loginDto.getPassword())) && u.isActive()) {
			LoginResponseDto result = DtoTools.convert(u, LoginResponseDto.class);
			// generate JWT TOKEN
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("user_id", u.getId());
			claims.put("user_fullName", u.getFirstName() + " " + u.getLastName());
			claims.put("user_role", u.getRole().toString());

			String token = jwtTokenUtil.doGenerateToken(claims, loginDto.getEmail());
			TokenSaver.tokensByEmail.put(u.getEmail(), token);
			// générer le token
			// le sauvegarder côté service pour pouvoir le vérifier lors des prochaines
			// requêtes
			result.setToken(token);
			return result;
		} else
			throw new Exception("Error : invalid credentials !");
	}

	@Override
	public UtilisateurDto findByEmail(String email) throws Exception {
		Utilisateur u = utilisateurRepository.findByEmail(email);
		if (u != null)
			return DtoTools.convert(u, UtilisateurDto.class);
		else
			throw new Exception("User not found !");
	}

	@Override
	public boolean resetPassword(ChangePwdDto changePwdObj) throws Exception {
		boolean expired = jwtTokenUtil.isTokenExpired(changePwdObj.getToken());
		if (expired)
			throw new Exception("Error : Expired token, ask for reset again !");

		String newPassword = HashTools.hashSHA512(changePwdObj.getPassword());

		// récupérer l'utilisateur par email
		String email = jwtTokenUtil.getUsernameFromToken(changePwdObj.getToken());
		Utilisateur u = utilisateurRepository.findByEmail(email);

		if (u != null) {
			String currentPwd = u.getPassword();

			if (newPassword.equals(currentPwd))
				throw new Exception("Error : updating with the same old password !");

			u.setPassword(newPassword);
			utilisateurRepository.saveAndFlush(u);
			return true;
		}
		return false;
	}

	@Override
	public List<UtilisateurDto> getByRole(Role role) {

		List<Utilisateur> utilisateurs = utilisateurRepository.findByRole(role);
		List<UtilisateurDto> utilisateursDto = new ArrayList<UtilisateurDto>();
		for (Utilisateur utilisateur : utilisateurs) {
			utilisateursDto.add(DtoTools.convert(utilisateur, UtilisateurDto.class));
		}
		return utilisateursDto;

	}

}
