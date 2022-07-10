package fr.dawan.cfa2022.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NiveauDto implements Serializable {

	private long id;
	
	private int valeur;
	
	private String description;
	
	private String codeCouleurHexa;
	
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
