package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.NiveauDto;
import fr.dawan.cfa2022.entities.Niveau;
import fr.dawan.cfa2022.repositories.NiveauRepository;
@Service
@Transactional
public class NiveauServiceImpl implements NiveauService {

	@Autowired
	private NiveauRepository niveauRepository;
	@Override
	public NiveauDto getById(long id) {
			Optional<Niveau> n = niveauRepository.findById(id);
		if (n.isPresent())
			return DtoTools.convert(n.get(), NiveauDto.class);

		return null;
	}

	@Override
	public NiveauDto saveOrUpdate(NiveauDto tDto) throws Exception {
		Niveau n = DtoTools.convert(tDto, Niveau.class);
		n = niveauRepository.saveAndFlush(n);
		return DtoTools.convert(n, NiveauDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = niveauRepository.countByDescriptionContaining(search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		niveauRepository.deleteById(id);		
	}

	@Override
	public List<NiveauDto> getAll() {
		List<Niveau> niveaux = niveauRepository.findAll();
		List<NiveauDto> result = new ArrayList<NiveauDto>();
		for (Niveau i : niveaux) {
			result.add(DtoTools.convert(i, NiveauDto.class));
		}
		return result;
	}

}
