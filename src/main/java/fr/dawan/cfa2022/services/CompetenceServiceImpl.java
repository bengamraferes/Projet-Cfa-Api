package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CompetenceDto;
import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.entities.Competence;
import fr.dawan.cfa2022.repositories.CompetenceRepository;
@Service
@Transactional
public class CompetenceServiceImpl implements CompetenceService {

	@Autowired
	private CompetenceRepository competenceRepository;
	
	@Override
	public CompetenceDto getById(long id) {
		Optional<Competence> c = competenceRepository.findById(id);
		if (c.isPresent())
			return DtoTools.convert(c.get(), CompetenceDto.class);

		return null;
	}

	@Override
	public CompetenceDto saveOrUpdate(CompetenceDto tDto) {
		Competence c = DtoTools.convert(tDto, Competence.class);
		c = competenceRepository.saveAndFlush(c);
		return DtoTools.convert(c, CompetenceDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = competenceRepository.countByTitreContainingOrDescriptionContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		competenceRepository.deleteById(id);		
	}

	@Override
	public List<CompetenceDto> getAllByTitreProfessionnelId(long id) {
		List<Competence> competences = competenceRepository.getAllByTitreProfessionnelId(id);
		List<CompetenceDto> result = new ArrayList<CompetenceDto>();
		for (Competence c : competences) {
			result.add(DtoTools.convert(c, CompetenceDto.class));
		}
		return result;
	}
	@Override
	public List<CompetenceDto> getAllByBlocComptenceId(long id, String search ) {
		List<Competence> competences = competenceRepository.getAllByBlocCompetencesId(id, search, search);
		List<CompetenceDto> result = new ArrayList<CompetenceDto>();
		for (Competence c : competences) {
			result.add(DtoTools.convert(c, CompetenceDto.class));
		}
		return result;
	}

	@Override
	public List<CompetenceDto> getAll(int page, int max, String search) {
		List<Competence> competences = competenceRepository.findByTitreContainingOrDescriptionContaining(search, search, PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<CompetenceDto> result = new ArrayList<CompetenceDto>();
		for (Competence c : competences) {
			result.add(DtoTools.convert(c, CompetenceDto.class));
		}
		return result;
	}



}
