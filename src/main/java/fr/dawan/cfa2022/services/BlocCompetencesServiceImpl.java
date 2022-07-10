package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.BlocCompetencesDto;
import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.entities.BlocCompetences;
import fr.dawan.cfa2022.repositories.BlocCompetencesRepository;
@Service
@Transactional
public class BlocCompetencesServiceImpl implements BlocCompetencesService {

	@Autowired
	private BlocCompetencesRepository blocCompetencesRepository;

	@Override
	public List<BlocCompetencesDto> getAllByTitreProId(long id) {
		List<BlocCompetences> BlocsCompetences = blocCompetencesRepository.getAllByTitreProfessionnelId(id);
		List<BlocCompetencesDto> result = new ArrayList<BlocCompetencesDto>();
		for (BlocCompetences t : BlocsCompetences) {
			result.add(DtoTools.convert(t, BlocCompetencesDto.class));
		}
		return result;
	}

	@Override
	public BlocCompetencesDto getById(long id) {
		
		Optional<BlocCompetences> b = blocCompetencesRepository.findById(id);
		if (b.isPresent())
			return DtoTools.convert(b.get(), BlocCompetencesDto.class);

		return null;
	}

	@Override
	public BlocCompetencesDto saveOrUpdate(BlocCompetencesDto tDto) {
		BlocCompetences b = DtoTools.convert(tDto, BlocCompetences.class);
		b = blocCompetencesRepository.saveAndFlush(b);
	
	return DtoTools.convert(b, BlocCompetencesDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = blocCompetencesRepository.countByTitreContainingOrDescriptionContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		blocCompetencesRepository.deleteById(id);		
	}

	@Override
	public List<BlocCompetencesDto> getAll(int page, int max, String search) {
		List<BlocCompetences> BlocsCompetences = blocCompetencesRepository.findByTitreContainingOrDescriptionContaining(search, search, PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<BlocCompetencesDto> result = new ArrayList<BlocCompetencesDto>();
		for (BlocCompetences t : BlocsCompetences) {
			result.add(DtoTools.convert(t, BlocCompetencesDto.class));
		}
		return result;
	}




}
