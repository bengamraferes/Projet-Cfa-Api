package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CountAvgDto;
import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.EvaluationDto;
import fr.dawan.cfa2022.entities.Evaluation;
import fr.dawan.cfa2022.repositories.EvaluationRepository;
@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Override
	public EvaluationDto getById(long id) {
		Optional<Evaluation> e = evaluationRepository.findById(id);
		if (e.isPresent())
			return DtoTools.convert(e.get(), EvaluationDto.class);

		return null;
	}

	@Override
	public EvaluationDto saveOrUpdate(EvaluationDto tDto) throws Exception {
		Evaluation e = DtoTools.convert(tDto, Evaluation.class);
		e = evaluationRepository.saveAndFlush(e);
	
		return DtoTools.convert(e, EvaluationDto.class);
	}

	@Override
	public CountDto count(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		evaluationRepository.deleteById(id);
		
	}

	@Override
	public List<EvaluationDto> findAllByEtudiantId(long id) {
		List<Evaluation> evaluations = evaluationRepository.findAllByEtudiantId(id);
		List<EvaluationDto> result = new ArrayList<EvaluationDto>();
		for (Evaluation e : evaluations) {
			result.add(DtoTools.convert(e, EvaluationDto.class));
		}
		return result;
	}

	@Override
	public CountAvgDto getAvgByEtudiantIdAndPromotionId(long etudiantId, long promotionId) {
		double nb =  evaluationRepository.getAvgByEtudiantIdAndPromotionId(etudiantId, promotionId);
		CountAvgDto d = new CountAvgDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public CountAvgDto getAvgByPromotionId(long promotionId) {
		double nb =  evaluationRepository.getAvgByPromotionId(promotionId);
		CountAvgDto d = new CountAvgDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public CountAvgDto getAvgByPromotionIdAndBlocDeCompetences(long bcId, long promotionId) {
		double nb =  evaluationRepository.getAvgByPromotionIdAndBlocDeCompetences(bcId, promotionId);
		CountAvgDto d = new CountAvgDto();
		d.setNb(nb);
		return d;		
	}

	@Override
	public CountAvgDto getAvgByEtudiantIdAndBlocCompId(long etudiantId, long blocCompetencesId) {
		double nb =  evaluationRepository.getAvgByEtudiantIdAndBlocCompId(etudiantId, blocCompetencesId);
		CountAvgDto d = new CountAvgDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public List<EvaluationDto> findAllByPromotionId(long promotionId) {
		List<Evaluation> evaluations = evaluationRepository.findAllByPromotionId(promotionId);
		List<EvaluationDto> result = new ArrayList<EvaluationDto>();
		for (Evaluation e : evaluations) {
			result.add(DtoTools.convert(e, EvaluationDto.class));
		}
		return result;
	}

	@Override
	public EvaluationDto findAllByEtudiantIdAndEpreuveId(long idEtudiant, long idEpreuve) {
		Optional<Evaluation> evaOpt = evaluationRepository.getByEtudiantIdAndEpreuveId(idEtudiant,idEpreuve);
		if (evaOpt.isPresent()) {
			return DtoTools.convert(evaOpt.get(), EvaluationDto.class);
		}
		
		return null;
	}



}
