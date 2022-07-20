package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DG2TrainingDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.FormationDto;
import fr.dawan.cfa2022.dto.LocationDto;
import fr.dawan.cfa2022.dto.VilleDto;
import fr.dawan.cfa2022.entities.Formation;
import fr.dawan.cfa2022.entities.Ville;
import fr.dawan.cfa2022.repositories.FormationRepository;
@Service
@Transactional
public class FormationServiceImpl implements FormationService{

	@Autowired
	private FormationRepository formationRepository;
	@Override
	public FormationDto getById(long id) {
		Optional<Formation> f = formationRepository.findById(id);
		if (f.isPresent())
			return DtoTools.convert(f.get(), FormationDto.class);

		return null;
	}

	@Override
	public FormationDto saveOrUpdate(FormationDto tDto) throws Exception {
		Formation f = DtoTools.convert(tDto, Formation.class);
		f = formationRepository.saveAndFlush(f);
		return DtoTools.convert(f, FormationDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = formationRepository.countByObjectifsPedagogiqueContainingOrTitreContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		formationRepository.deleteById(id);
		
	}

	@Override
	public List<FormationDto> getAll(int page, int max, String search) {
		List<Formation> formations = formationRepository.findByObjectifsPedagogiqueContainingOrTitreContaining(search, search, PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<FormationDto> result = new ArrayList<FormationDto>();
		for (Formation f : formations) {
			result.add(DtoTools.convert(f, FormationDto.class));
		}
		return result;
	}

	@Override
	public List<FormationDto> getAll() {
		List<Formation> formations = formationRepository.findAll();
		List<FormationDto> result = new ArrayList<FormationDto>();
		for (Formation f : formations) {
			result.add(DtoTools.convert(f, FormationDto.class));
		}
		return result;
	}
	@Override
	public int updateFromDg2() throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

		ResponseEntity<String> rep = restTemplate.getForEntity("https://dawan.org/public/training/", String.class);
				
		// GET
		int nb = 0;
		if (rep.getStatusCode() == HttpStatus.OK) {

			DG2TrainingDto[] formations = mapper.readValue(rep.getBody(), DG2TrainingDto[].class);
			for (DG2TrainingDto formationDto : formations) {
				Optional<Formation> fDb = formationRepository.findBySlug(formationDto.getSlug()) ;
				if(fDb.isPresent()) {
					continue;
				}
				FormationDto fDto = new FormationDto();
				fDto.setTitre(formationDto.getTitle());
				fDto.setSlug(formationDto.getSlug());
				fDto.setDuree(Double.parseDouble(formationDto.getDuration()));
				fDto.setObjectifsPedagogique(formationDto.getObjectives());
				saveOrUpdate(fDto);
				nb++;
			}
		}
		return nb;
	}

}
