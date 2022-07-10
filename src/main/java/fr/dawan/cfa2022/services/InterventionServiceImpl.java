package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.InterventionDto;
import fr.dawan.cfa2022.entities.Intervention;
import fr.dawan.cfa2022.entities.Utilisateur;
import fr.dawan.cfa2022.entities.Utilisateur.Role;
import fr.dawan.cfa2022.repositories.InterventionRepository;
import fr.dawan.cfa2022.repositories.UtilisateurRepository;

@Service
@Transactional
public class InterventionServiceImpl implements InterventionService {

	@Autowired
	private InterventionRepository interventionRepository;
	
	@Autowired UtilisateurRepository utilisateurRepository; 
	@Override
	public InterventionDto getById(long id) {
		Optional<Intervention> i = interventionRepository.findById(id);
		if (i.isPresent())
			return DtoTools.convert(i.get(), InterventionDto.class);

		return null;
	}

	@Override
	public InterventionDto saveOrUpdate(InterventionDto tDto) throws Exception {
		Intervention i = DtoTools.convert(tDto, Intervention.class);
		i = interventionRepository.saveAndFlush(i);
		return DtoTools.convert(i, InterventionDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = interventionRepository.countByFormationTitreContainingOrFormationObjectifsPedagogiqueContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		
		interventionRepository.deleteById(id);
	}

	@Override
	public List<InterventionDto> getAllByFormationId(long idFromation) {
		List<Intervention> interventions = interventionRepository.getAllByFormationId(idFromation);
		List<InterventionDto> result = new ArrayList<InterventionDto>();
		for (Intervention i : interventions) {
			result.add(DtoTools.convert(i, InterventionDto.class));
		}
		return result;
	}

	@Override
	public List<InterventionDto> getAllByPromotionId(long idPromotion) {
		List<Intervention> interventions = interventionRepository.getAllByPromotionId(idPromotion);
		List<InterventionDto> result = new ArrayList<InterventionDto>();
		for (Intervention i : interventions) {
			result.add(DtoTools.convert(i, InterventionDto.class));
		}
		return result;
	}

	@Override
	public List<InterventionDto> getAllByFormateurId(long idUtilisateur) throws Exception {
		Utilisateur utilisateurDb = utilisateurRepository.getOne(idUtilisateur);
		
		if (utilisateurDb.getRole() != Role.FORMATEUR) {
			throw new Exception("L'utilisateur selmectioné n'est pas un Formateur");
		}
		List<Intervention> interventions = interventionRepository.getAllByFormateurId(idUtilisateur);
		List<InterventionDto> result = new ArrayList<InterventionDto>();
		for (Intervention i : interventions) {
			result.add(DtoTools.convert(i, InterventionDto.class));
		}
		return result;
	}

	@Override
	public List<InterventionDto> getAll(int page, int max, String search) {
		List<Intervention> interventions = interventionRepository.findByFormationTitreContainingOrFormationObjectifsPedagogiqueContaining(search, search, PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<InterventionDto> result = new ArrayList<InterventionDto>();
		for (Intervention i : interventions) {
			result.add(DtoTools.convert(i, InterventionDto.class));
		}
		return result;
	}

}
