package fr.dawan.cfa2022.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
public class Niveau implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Min(0)
	@Max(5)
	private int valeur;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@Column(length = 6)
	private String codeCouleurHexa;
	
//		  enum niveau {
//		    DEBUTANT ("ffff00",1);
//
//			niveau(String string, int i) {
//				// TODO Auto-generated constructor stub
//			}
//		  }
	@Version
	private int version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodeCouleurHexa() {
		return codeCouleurHexa;
	}

	public void setCodeCouleurHexa(String codeCouleurHexa) {
		this.codeCouleurHexa = codeCouleurHexa;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
