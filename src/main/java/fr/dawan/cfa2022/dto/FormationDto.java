package fr.dawan.cfa2022.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FormationDto implements Serializable {

private long id;
	
	
	private String titre;
	
	private Double duree;
	
	private String objectifsPedagogique;

	private String slug;


	private int version;

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

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public String getObjectifsPedagogique() {
		return objectifsPedagogique;
	}

	public void setObjectifsPedagogique(String objectifsPedagogique) {
		this.objectifsPedagogique = objectifsPedagogique;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	
}
