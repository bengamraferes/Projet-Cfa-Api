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
	

	private long versionId;
	
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

	public long getVersionId() {
		return versionId;
	}

	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}


	
	
}
