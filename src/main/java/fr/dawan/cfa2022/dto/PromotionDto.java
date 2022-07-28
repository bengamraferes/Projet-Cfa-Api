package fr.dawan.cfa2022.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@SuppressWarnings("serial")
public class PromotionDto implements Serializable  {

	
	private long id;
	
	private LocalDate dateDebut;
	
	private LocalDate dateFin;
	

	private long titreProfessionnelId;
	

	private long villeId;
	

	private long version;
	
	private String villeNom;
	
	private String titreProfessionnelTitre;
	
	
	private List<Long> etudiantsId;
	
	
	public List<Long> getEtudiantsId() {
		return etudiantsId;
	}

	public void setEtudiantsId(List<Long> etudiantsId) {
		this.etudiantsId = etudiantsId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public long getTitreProfessionnelId() {
		return titreProfessionnelId;
	}

	public void setTitreProfessionnelId(long titreProfessionnelId) {
		this.titreProfessionnelId = titreProfessionnelId;
	}

	public long getVilleId() {
		return villeId;
	}

	public void setVilleId(long villeId) {
		this.villeId = villeId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long versionId) {
		this.version = versionId;
	}

	public String getVilleNom() {
		return villeNom;
	}

	public void setVilleNom(String villeNom) {
		this.villeNom = villeNom;
	}

	public String getTitreProfessionnelTitre() {
		return titreProfessionnelTitre;
	}

	public void setTitreProfessionnelTitre(String titreProfessionnelTitre) {
		this.titreProfessionnelTitre = titreProfessionnelTitre;
	}


	
	
}
