package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.PositionnementDto;
import fr.dawan.cfa2022.entities.Positionnement;
import fr.dawan.cfa2022.repositories.PositionnementRepository;

@Service
@Transactional
public class PositionnementServiceImpl implements PositionnementService {

	@Autowired
	private PositionnementRepository positionnementRepository;
	
	@Override
	public PositionnementDto getById(long id) {
		Optional<Positionnement> p = positionnementRepository.findById(id);
		if (p.isPresent())
			return DtoTools.convert(p.get(), PositionnementDto.class);

		return null;
	}

	@Override
	public PositionnementDto saveOrUpdate(PositionnementDto tDto) throws Exception {
		Positionnement p = DtoTools.convert(tDto, Positionnement.class);
		p = positionnementRepository.saveAndFlush(p);
		return DtoTools.convert(p, PositionnementDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = positionnementRepository.countByEtudiantLastNameContainingOrEtudiantFirstNameContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {

		positionnementRepository.deleteById(id);
	}

	@Override
	public List<PositionnementDto> getAllByInterventionId(long idIntervention) {
		List<Positionnement> positionnements = positionnementRepository.getAllByInterventionId(idIntervention);
		List<PositionnementDto> result = new ArrayList<PositionnementDto>();
		for (Positionnement p : positionnements) {
			result.add(DtoTools.convert(p, PositionnementDto.class));
		}
		return result;
	}

	@Override
	public List<PositionnementDto> getAllByPromotionId(long idPromotion) {
		List<Positionnement> positionnements = positionnementRepository.getAllByPromotionId(idPromotion);
		List<PositionnementDto> result = new ArrayList<PositionnementDto>();
		for (Positionnement p : positionnements) {
			result.add(DtoTools.convert(p, PositionnementDto.class));
		}
		return result;
	}

}
