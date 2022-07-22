		package fr.dawan.cfa2022.entities;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Etudiant  extends Utilisateur  {

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Promotion> promotions ;

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
	public List<Long> getPromotionsId(){
		List<Long> promotionsId = new ArrayList<Long>();
		if (promotions != null) {
			for(Promotion promo : promotions) {
				promotionsId.add(promo.getId());
			}
		}
		return promotionsId;
}
	

}
//proposé par Mohamed
//Dans la classe promotion :
//
//@ManyToMany(cascade = CascadeType.ALL)
//	private List<Etudiant> etudiants;
//    
//    
//Dans la classe étudiants :
//	@ManyToMany(mappedBy = "etudiants", cascade = CascadeType.ALL)
//	private List<Promotion> promotions;
//    
//Dans le promotionServiceImpl : 
//@Override
//	public PromotionDto saveOrUpdate(PromotionDto uDto) throws Exception {
//		Promotion promo = DtoTools.convert(uDto, Promotion.class);
//		if (uDto.getEtudiantsId() != null) {
//			for (long id : uDto.getEtudiantsId()) {
//				Optional<Etudiant> opt = etudiantRepository.findById(id);
//				if (opt.isPresent()) {
//					Etudiant etu = opt.get();
//					promo.getEtudiants().add(etu);
//					etu.getPromotions().add(promo);
//				}
//				promo.getEtudiants().remove(null);
//			}
//		}
//		promo = repository.saveAndFlush(promo);
//		return DtoTools.convert(promo, PromotionDto.class);
//	}
    