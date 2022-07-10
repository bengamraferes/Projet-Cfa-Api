package fr.dawan.cfa2022.dto;

import java.io.Serializable;

import fr.dawan.cfa2022.entities.Epreuve.Type;
@SuppressWarnings("serial")
public class EpreuveDto implements Serializable {

	
	private long id;
	
	
	private String titre;

	private String description;
	

	private Type type;
	
	private int version;
	
	private long  blocCompetencesId;
	 
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
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public long getBlocCompetencesId() {
		return blocCompetencesId;
	}
	public void setBlocCompetencesId(long blocCompetencesId) {
		this.blocCompetencesId = blocCompetencesId;
	}


}
