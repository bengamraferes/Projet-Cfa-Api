package fr.dawan.cfa2022.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.LocationDto;
import fr.dawan.cfa2022.dto.VilleDto;
import fr.dawan.cfa2022.entities.Ville;
import fr.dawan.cfa2022.repositories.VilleRepository;
@Service
@Transactional

public class VilleServiceImpl implements VilleService {

	@Autowired
	private VilleRepository villeRepository;
	
	@Override
	public VilleDto getById(long id) {
		Optional<Ville> v = villeRepository.findById(id);
		if (v.isPresent())
			return DtoTools.convert(v.get(), VilleDto.class);

		return null;
	}

	@Override
	public VilleDto saveOrUpdate(VilleDto tDto) throws Exception {
		Ville v = DtoTools.convert(tDto, Ville.class);
		v = villeRepository.saveAndFlush(v);
	
		return DtoTools.convert(v, VilleDto.class);
		
	}
	@Override
	public CountDto count(String search) {
		long nb = villeRepository.countByNomContaining(search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;		
	}

	@Override
	public void delete(long id) {
		villeRepository.deleteById(id);		
	}
	@Override
	public int updateFromDg2() throws Exception {
		RestTemplate restTemplate = new RestTemplate();// objet permettant de faire des requêtes HTTP

		ObjectMapper mapper = new ObjectMapper(); // objet de la librairie Jackson permettant de convertir de json>objet
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

		ResponseEntity<String> rep = restTemplate.getForEntity("https://dawan.org/public/location/", String.class);// req
				
		// GET
		int nb = 0;
		if (rep.getStatusCode() == HttpStatus.OK) {

			LocationDto[] villes = mapper.readValue(rep.getBody(), LocationDto[].class);
			// traitement à faire avec les localisations récupérées
			for (LocationDto locDto : villes) {
//				VilleDto vDto = DtoTools.convert(locDto, VilleDto.class);
				VilleDto vDto = new VilleDto();
				vDto.setNom(locDto.getName());
				vDto.setSlug(locDto.getSlug());
				// vérifier qu'elles n'existent pas en bdd (comparaison par rapport au slug)
				// puis insertion s'il n'existe pas
				Ville v = null;
				try {
					v = villeRepository.findBySlug(vDto.getSlug());

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (v == null) {
					saveOrUpdate(vDto);
					nb++;
				}
			}
		}
		return nb;
	}

}
