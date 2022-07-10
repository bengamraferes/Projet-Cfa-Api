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
import fr.dawan.cfa2022.dto.TitreProfessionnelDto;
import fr.dawan.cfa2022.entities.TitreProfessionnel;
import fr.dawan.cfa2022.repositories.TitreProfessionnelRepository;
		
@Service
@Transactional
public class TitreProfessionnelServiceImpl implements TitreProfessionnelService{
	@Autowired
	private TitreProfessionnelRepository tProfessionnelRepository;
	
	@Override
	public TitreProfessionnelDto getById(long id) {
		Optional<TitreProfessionnel> t = tProfessionnelRepository.findById(id);
		if (t.isPresent())
			return DtoTools.convert(t.get(), TitreProfessionnelDto.class);

		return null;
	}

	@Override
	public TitreProfessionnelDto saveOrUpdate(TitreProfessionnelDto tDto) {
			TitreProfessionnel t = DtoTools.convert(tDto, TitreProfessionnel.class);
			t = tProfessionnelRepository.saveAndFlush(t);
		
		return DtoTools.convert(t, TitreProfessionnelDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = tProfessionnelRepository.countByTitreContaining(search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;

	}

	@Override
	public void delete(long id) {
		tProfessionnelRepository.deleteById(id);		
	}

	@Override
	public List<TitreProfessionnelDto> getAll() {
		List<TitreProfessionnel> titreProfessionnels = tProfessionnelRepository.findAll();
		List<TitreProfessionnelDto> result = new ArrayList<TitreProfessionnelDto>();
		for (TitreProfessionnel t : titreProfessionnels) {
			result.add(DtoTools.convert(t, TitreProfessionnelDto.class));
		}
		return result;
	}

	@Override
	public List<TitreProfessionnelDto> getAll(int page, int max, String search) {
		List<TitreProfessionnel> titreProfessionnels = tProfessionnelRepository.findByTitreContaining(search,PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<TitreProfessionnelDto> result = new ArrayList<TitreProfessionnelDto>();
		for (TitreProfessionnel t : titreProfessionnels) {
			result.add(DtoTools.convert(t, TitreProfessionnelDto.class));
		}
		return result;
	}

	@Override
	public TitreProfessionnelDto getTitreProfessionnelByPromotionId(long promotionId) {
		Optional<TitreProfessionnel> t = tProfessionnelRepository.getTitreProfessionnelByPromotionId(promotionId);
		if (t.isPresent())
			return DtoTools.convert(t.get(), TitreProfessionnelDto.class);

		return null;
	}

	


}
