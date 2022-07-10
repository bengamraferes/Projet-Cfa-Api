package fr.dawan.cfa2022.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class EtudiantDto  extends UtilisateurDto implements Serializable  {

	
	private List<Long> promotionsId ;
	

	public List<Long> getPromotionsId() {
		return promotionsId;
	}

	public void setPromotionsId(List<Long> promotionsId) {
		this.promotionsId = promotionsId;
	}

	
	
}
