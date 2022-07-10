package fr.dawan.cfa2022.dto;

import java.time.LocalDate;
import java.util.Map;

import fr.dawan.cfa2022.entities.Positionnement;

public class GrillePositionnementDto {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private String module;

	private String formateur;

	private String objectifPedagogiques;

	private Map<String, Positionnement> etudiantsPositionnements;



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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getFormateur() {
		return formateur;
	}

	public void setFormateur(String formateur) {
		this.formateur = formateur;
	}

	public String getObjectifPedagogiques() {
		return objectifPedagogiques;
	}

	public void setObjectifPedagogiques(String objectifPedagogiques) {
		this.objectifPedagogiques = objectifPedagogiques;
	}

	public Map<String, Positionnement> getEtudiantsPositionnements() {
		return etudiantsPositionnements;
	}

	public void setEtudiantsPositionnements(Map<String, Positionnement> etudiantsPositionnements) {
		this.etudiantsPositionnements = etudiantsPositionnements;
	}

}
