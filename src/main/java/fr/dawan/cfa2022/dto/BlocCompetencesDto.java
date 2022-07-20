package fr.dawan.cfa2022.dto;

import java.io.Serializable;
@SuppressWarnings("serial")
public class BlocCompetencesDto implements Serializable  {

	
	private long id;
	
	private String titre;
	
	private String description;
	
	private long titreProfessionnelId;

	private int version;
	
	
	public BlocCompetencesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BlocCompetencesDto(long id, String titre, String description, long titreProfessionnelId, int version) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.titreProfessionnelId = titreProfessionnelId;
		this.version = version;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public long getTitreProfessionnelId() {
		return titreProfessionnelId;
	}
	public void setTitreProfessionnelId(long titreProfessionnelId) {
		this.titreProfessionnelId = titreProfessionnelId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
