package fr.dawan.cfa2022.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.EtudiantDto;
import fr.dawan.cfa2022.dto.UtilisateurDto;
import fr.dawan.cfa2022.entities.Etudiant;
import fr.dawan.cfa2022.entities.Promotion;
import fr.dawan.cfa2022.entities.Utilisateur.Role;
import fr.dawan.cfa2022.repositories.EtudiantRepository;
import fr.dawan.cfa2022.repositories.PromotionRepository;
import fr.dawan.cfa2022.tools.HashTools;

@Transactional
@Service
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	private EtudiantRepository etudiantRepository;
 	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public EtudiantDto getById(long id) {
		Optional<Etudiant> ut =  etudiantRepository.findById(id);
		if (ut.isPresent()) {
			return DtoTools.convert(ut.get(), EtudiantDto.class);
		}
		return null;		
	}

	@Override
	public EtudiantDto saveOrUpdate(EtudiantDto tDto) throws Exception  {
		Etudiant e =  DtoTools.convert(tDto, Etudiant.class);
		Role eType = Role.valueOf(tDto.getRole());
		if (eType!= Role.ETUDIANT) 
			throw new Exception("L'utilisateur renseigné n'est pas un Etudiant  ");	
	
		if (tDto.getPromotionsId().isEmpty() || tDto.getPromotionsId() == null) 
			throw new Exception("Insertion impossible merci de renseigner au moin une Promotion ");
		try {
			if (e.getId() == 0) { 
				e.setCreationDate(LocalDateTime.now());
				e.setPassword(HashTools.hashSHA512(e.getPassword()));
			} else {
				UtilisateurDto userInDb = getById(e.getId());
				if (!userInDb.getPassword().contentEquals(e.getPassword())) {
					e.setPassword(HashTools.hashSHA512(e.getPassword()));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		
		List<Promotion> promotions = new ArrayList<Promotion>();
		for (long idP : tDto.getPromotionsId()) {
			promotions.add(promotionRepository.getOne(idP));
		}
		e.setPromotions(promotions);
		e = etudiantRepository.saveAndFlush(e);
		return DtoTools.convert(e, EtudiantDto.class);
	}

	@Override
	public CountDto count(String search) {
		
		return utilisateurService.count(search);
	}

	@Override
	public void delete(long id) {
		etudiantRepository.deleteById(id);		
	}

	@Override
	public List<EtudiantDto> getAllByPromotionId(long id) {
		List<Etudiant> etudiants = etudiantRepository.findAllByPromoId(id);
		List<EtudiantDto> etudiantsDto = new ArrayList<EtudiantDto>();
		for (Etudiant e : etudiants) {
			etudiantsDto.add(DtoTools.convert(e, EtudiantDto.class));
		}
		return etudiantsDto;
	
	}

	

}
