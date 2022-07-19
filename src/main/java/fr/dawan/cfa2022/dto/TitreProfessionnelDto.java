package fr.dawan.cfa2022.dto;

import java.io.Serializable;
@SuppressWarnings("serial")
public class TitreProfessionnelDto implements Serializable {

	
	private long id;
	

	private String titre;
	
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
