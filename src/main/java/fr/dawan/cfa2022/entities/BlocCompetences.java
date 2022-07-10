package fr.dawan.cfa2022.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class BlocCompetences  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true )
	private String titre;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@ManyToOne
	private TitreProfessionnel titreProfessionnel;
	
	@Version
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TitreProfessionnel getTitreProfessionnel() {
		return titreProfessionnel;
	}
	public void setTitreProfessionnel(TitreProfessionnel titreProfessionnel) {
		this.titreProfessionnel = titreProfessionnel;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
}
