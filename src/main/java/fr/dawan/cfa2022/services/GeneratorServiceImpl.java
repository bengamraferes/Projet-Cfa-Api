package fr.dawan.cfa2022.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import fr.dawan.cfa2022.dto.BultinEvalDto;
import fr.dawan.cfa2022.dto.GrillePositionnementDto;
import fr.dawan.cfa2022.entities.BlocCompetences;
import fr.dawan.cfa2022.entities.Competence;
import fr.dawan.cfa2022.entities.Etudiant;
import fr.dawan.cfa2022.entities.Formation;
import fr.dawan.cfa2022.entities.Intervention;
import fr.dawan.cfa2022.entities.Niveau;
import fr.dawan.cfa2022.entities.Positionnement;
import fr.dawan.cfa2022.entities.Promotion;
import fr.dawan.cfa2022.entities.TitreProfessionnel;
import fr.dawan.cfa2022.repositories.BlocCompetencesRepository;
import fr.dawan.cfa2022.repositories.CompetenceRepository;
import fr.dawan.cfa2022.repositories.EtudiantRepository;
import fr.dawan.cfa2022.repositories.EvaluationRepository;
import fr.dawan.cfa2022.repositories.FormationRepository;
import fr.dawan.cfa2022.repositories.InterventionRepository;
import fr.dawan.cfa2022.repositories.NiveauRepository;
import fr.dawan.cfa2022.repositories.PositionnementRepository;
import fr.dawan.cfa2022.repositories.PromotionRepository;
import fr.dawan.cfa2022.repositories.TitreProfessionnelRepository;
import fr.dawan.cfa2022.tools.ToPdf;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
@Transactional
public class GeneratorServiceImpl implements GeneratorService {

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private TitreProfessionnelRepository titreProfessionnelRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired

	private BlocCompetencesRepository blocCompetencesRepository;

	@Autowired
	private CompetenceRepository competenceRepository;

	@Autowired
	private InterventionRepository interventionRepository;

	@Autowired
	private NiveauRepository niveauRepository;

	@Autowired
	private PositionnementRepository positionnementRepository;

	@Autowired
	private Configuration freemarkerConfig;

	@Value("${app.storagefolder}")
	private String storageFolder;

	public String getStorageFolder() {
		return storageFolder;
	}

	public void setStorageFolder(String storageFolder) {
		this.storageFolder = storageFolder;
	}

	@Override
	public String getBulinEval(long idEtudiant, long idPromotion) throws Exception {

		Optional<Etudiant> etudiant = etudiantRepository.findById(idEtudiant);
		// on définit ici le chemin où il va chercher les fichiers de templates
		if (!etudiant.isPresent()) {
			throw new Exception("Etudiant non trouvé");
		}
		Optional<TitreProfessionnel> titreProfessionnel = titreProfessionnelRepository
				.getTitreProfessionnelByPromotionId(idPromotion);

		if (!titreProfessionnel.isPresent()) {
			throw new Exception("TitreProfessionnel non trouvé");
		}

		Optional<Promotion> promotion = promotionRepository.findById(idPromotion);

		if (!promotion.isPresent()) {
			throw new Exception("Promotion non trouvé");
		}

		List<BlocCompetences> bCompetences = blocCompetencesRepository
				.getAllByTitreProfessionnelId(titreProfessionnel.get().getId());

		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

		// charger le template titrepro.ftl et lui envoyer l'objet t
		Template template = freemarkerConfig.getTemplate("BultinEval.ftl");
		Map<BlocCompetences, List<Competence>> blocsC = new HashMap<BlocCompetences, List<Competence>>();
//		Map<Double, Double> moyenneEtudiantPromotion = new HashMap<Double, Double>();
		List<Double> moyenneEtudiant = new ArrayList<Double>();
		List<Double> moyennePromotion = new ArrayList<Double>();
		for (BlocCompetences blocCompetences : bCompetences) {
			List<Competence> competences = competenceRepository.getAllByBlocCompetencesId(blocCompetences.getId());
			blocsC.put(blocCompetences, competences);
			moyenneEtudiant
					.add(evaluationRepository.getAvgByEtudiantIdAndBlocCompId(idEtudiant, blocCompetences.getId()));
			moyennePromotion.add(
					evaluationRepository.getAvgByPromotionIdAndBlocDeCompetences(blocCompetences.getId(), idPromotion));

		}
		BultinEvalDto be = new BultinEvalDto();
		be.setFirstName(etudiant.get().getFirstName());
		be.setLastName(etudiant.get().getLastName());
		be.setDate(promotion.get().getDateDebut());
		be.setTitre(titreProfessionnel.get().getTitre());
		be.setBlocsComp(blocsC);
		be.setMoyenneEtudiant(moyenneEtudiant);
		be.setMoyennePromotion(moyennePromotion);
		be.setMoyenneGeneraleEtudiant(evaluationRepository.getAvgByEtudiantIdAndPromotionId(idEtudiant, idPromotion));
		be.setMoyenneGeneralePromotion(evaluationRepository.getAvgByPromotionId(idPromotion));
		System.out.println(be.getTitre());
//		Map<String, Object> model = new HashMap<String, Object>();
//		
		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, be);

		// Convert to Pdf
		String outputPdf = storageFolder + "/BultinEval " + etudiant.get().getLastName() + "-"
				+ etudiant.get().getFirstName() + ".pdf";
		ToPdf.convertHtmlToPdf(htmlContent, outputPdf);

		return outputPdf;
	}

	@Override
	public String getGrillePositionnement(long idPromotion) throws Exception {
		Optional<Promotion> promotion = promotionRepository.findById(idPromotion);

		if (!promotion.isPresent())
			throw new Exception("Promotion non trouvé");

		List<Intervention> interventions = interventionRepository.getAllByPromotionId(idPromotion);

		if (interventions.isEmpty() || interventions == null)
			throw new Exception("Pas d'interventions pour cette promotion");

		Map<String, List<?>> gp = new HashMap<String, List<?>>();
		List<GrillePositionnementDto> grillesPositionnements = new ArrayList<GrillePositionnementDto>();
		for (Intervention i : interventions) {

			GrillePositionnementDto gpd = new GrillePositionnementDto();
			gpd.setDateDebut(i.getDateDebut());
			gpd.setDateFin(i.getDateFin());
			Formation f = i.getFormation();
			gpd.setModule(f.getTitre());
			gpd.setObjectifPedagogiques(f.getObjectifsPedagogique());
			gpd.setFormateur(i.getFormateur().getFirstName() + " " + i.getFormateur().getLastName());

			Map<String, Positionnement> etudiantsPositionnement = new HashMap<String, Positionnement>();
			List<Positionnement> positionnements =  positionnementRepository.getAllByInterventionId(i.getId());
			for (Positionnement p : positionnements) {
				etudiantsPositionnement.put(p.getEtudiant().getFirstName() + " " +p.getEtudiant().getLastName(), p);
			}
			gpd.setEtudiantsPositionnements(etudiantsPositionnement);
			grillesPositionnements.add(gpd);
		}
		gp.put("interventions", grillesPositionnements);
		List<Niveau> niveaux = niveauRepository.findAll(Sort.by("valeur"));
		gp.put("niveaux", niveaux);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate("GrillePositionnement.ftl");

		String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, gp);

		String outputPdf = storageFolder + "/GrillePositionnement"+promotion.get().getTitreProfessionnel().getTitre() +".pdf";
		ToPdf.convertHtmlToPdf(htmlContent, outputPdf);

		return outputPdf;
	}

}
