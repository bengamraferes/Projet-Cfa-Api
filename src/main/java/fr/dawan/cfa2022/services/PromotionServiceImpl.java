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
import fr.dawan.cfa2022.dto.PromotionDto;
import fr.dawan.cfa2022.entities.Etudiant;
import fr.dawan.cfa2022.entities.Promotion;
import fr.dawan.cfa2022.repositories.EtudiantRepository;
import fr.dawan.cfa2022.repositories.PromotionRepository;
@Service
@Transactional
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository; 
	
	@Override
	public PromotionDto getById(long id) {
		Optional<Promotion> p = promotionRepository.findById(id);
		if (p.isPresent())
			return DtoTools.convert(p.get(), PromotionDto.class);

		return null;
		
	}

	@Override
	public PromotionDto saveOrUpdate(PromotionDto tDto) throws Exception {
		Promotion p = DtoTools.convert(tDto, Promotion.class);
		
		if (!tDto.getEtudiantsId().isEmpty() || tDto.getEtudiantsId() != null) {
			List<Etudiant> etudiants = new ArrayList<Etudiant>();
			for (long idE : tDto.getEtudiantsId()) {
				
				etudiants.add(etudiantRepository.getOne(idE));
			}
			p.setEtudiants(etudiants);
		}
		p = promotionRepository.saveAndFlush(p);
	
		return DtoTools.convert(p, PromotionDto.class);

	}

	@Override
	public CountDto count(String search) {
		long nb = promotionRepository.countByTitreProfessionnelTitreContainingOrVilleNomContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		promotionRepository.deleteById(id);
	}

	@Override
	public List<PromotionDto> getAll() {
		List<Promotion> promotions = promotionRepository.getAll();
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		for (Promotion p : promotions) {
			result.add(DtoTools.convert(p, PromotionDto.class));
		}
		return result;
	}

	@Override
	public List<PromotionDto> getAll(int page, int max, String search) {
		List<Promotion> promotions = promotionRepository.findByTitreProfessionnelTitreContainingOrVilleNomContaining(search,search,PageRequest.of(page, max)).get().collect(Collectors.toList());
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		for (Promotion p : promotions) {
			result.add(DtoTools.convert(p, PromotionDto.class));
		}
		return result ;		
	}
	@Override
	public List<PromotionDto> getAllByTitreProId(long id) {
		List<Promotion> promotions = promotionRepository.getAllByTitreProfessionnelId(id);
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		for (Promotion p : promotions) {
			result.add(DtoTools.convert(p, PromotionDto.class));
		}
		return result;
	}

	@Override
	public List<PromotionDto> getAllByVilleId(long id) {
		List<Promotion> promotions = promotionRepository.getAllByVilleId(id);
		List<PromotionDto> result = new ArrayList<PromotionDto>();
		for (Promotion p : promotions) {
			result.add(DtoTools.convert(p, PromotionDto.class));
		}
		return result;
	}

}
