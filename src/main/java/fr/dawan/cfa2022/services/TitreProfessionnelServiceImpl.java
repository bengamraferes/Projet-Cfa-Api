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
//	@Override
//	public int importFromDG2() throws Exception {
//		RestTemplate restTemplate = new RestTemplate();// objet permettant de faire des requêtes HTTP
//
//		ObjectMapper mapper = new ObjectMapper(); // objet de la librairie Jackson permettant de convertir de json>objet
//		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//
//		ResponseEntity<String> rep = restTemplate
//				.getForEntity("https://dawan.org/public/training/search?keywords=titre-professionnel", String.class);// req
//
//		// GET
//		int nb = 0;
//		if (rep.getStatusCode() == HttpStatus.OK) {
//			
//			TypeReference<Map<String,DG2TitreProDto>> typeRef = new TypeReference<Map<String,DG2TitreProDto>>(){}; 
//			
//			Map<String,DG2TitreProDto> results = mapper.readValue(rep.getBody(), typeRef);
//			for(String key : results.keySet()) {
//				DG2TitreProDto titreProResult = results.get(key);
//				DG2TrainingDto trainingObj = titreProResult.getTraining();
//				
//				TitreProfessionnel tp = new TitreProfessionnel();
//				tp.setTitre(trainingObj.getTitle());
//				tp.setSlug(trainingObj.getSlug());
//				
//				TitreProfessionnel v = null;
//				try {
//					v = repository.findBySlug(tp.getSlug());
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				if (v == null) {
//					repository.saveAndFlush(tp);
//					nb++;
//				}
//			}
//		}
//		return nb;
//	}
	


}
