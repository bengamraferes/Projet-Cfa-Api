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
import fr.dawan.cfa2022.dto.EpreuveDto;
import fr.dawan.cfa2022.entities.Epreuve;
import fr.dawan.cfa2022.entities.Epreuve.Type;
import fr.dawan.cfa2022.repositories.EpreuveRepository;
@Service
@Transactional
public class EpreuveServiceImpl implements EpreuveService {

	@Autowired
	private EpreuveRepository epreuveRepository;
	
	@Override
	public EpreuveDto getById(long id) {
		Optional<Epreuve> e = epreuveRepository.findById(id);
		if (e.isPresent())
			return DtoTools.convert(e.get(), EpreuveDto.class);

		return null;
	}

	@Override
	public EpreuveDto saveOrUpdate(EpreuveDto tDto) throws Exception {
		Epreuve e = DtoTools.convert(tDto, Epreuve.class);
		e = epreuveRepository.saveAndFlush(e);
		return DtoTools.convert(e, EpreuveDto.class);
		
	}

	@Override
	public CountDto count(String search) {
		long nb = epreuveRepository.countByTitreContainingOrDescriptionContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;		
	}

	@Override
	public void delete(long id) {
		
		epreuveRepository.deleteById(id);
	}

	@Override
	public List<EpreuveDto> findAllByblocCompetencesId(long id) {
		List<Epreuve> epreuves = epreuveRepository.getAllByBlocCompetencesId(id);
		List<EpreuveDto> result = new ArrayList<EpreuveDto>();
		for (Epreuve e : epreuves) {
			result.add(DtoTools.convert(e, EpreuveDto.class));
		}
		return result;
	}



	@Override
	public List<EpreuveDto> getAll(int page, int max, String search) {
		List<Epreuve> epreuves = epreuveRepository.findByTitreContainingOrDescriptionContaining(search, search, PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<EpreuveDto> result = new ArrayList<EpreuveDto>();
		for (Epreuve e : epreuves) {
			result.add(DtoTools.convert(e, EpreuveDto.class));
		}
		return result;
	}

	@Override
	public List<EpreuveDto> findAllByType(Type type) {
		List<Epreuve> epreuves = epreuveRepository.getAllByType(type);
		List<EpreuveDto> result = new ArrayList<EpreuveDto>();
		for (Epreuve e : epreuves) {
			result.add(DtoTools.convert(e, EpreuveDto.class));
		}
		return result;
	}

}
