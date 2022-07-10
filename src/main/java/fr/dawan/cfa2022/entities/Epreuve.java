package fr.dawan.cfa2022.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
@SuppressWarnings("serial")
@Entity
public class Epreuve implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true )
	private String titre;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Version
	private int version;
	
	public enum Type{
		QCM, MES
	}
	@ManyToOne
	private BlocCompetences  blocCompetences;
	 
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
	public BlocCompetences getBlocCompetences() {
		return blocCompetences;
	}
	public void setBlocCompetences(BlocCompetences blocCompetences) {
		this.blocCompetences = blocCompetences;
	}
	 
	 
}
