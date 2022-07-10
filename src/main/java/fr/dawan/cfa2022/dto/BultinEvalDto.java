package fr.dawan.cfa2022.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import fr.dawan.cfa2022.entities.BlocCompetences;
import fr.dawan.cfa2022.entities.Competence;

public class BultinEvalDto {

	private  String firstName;
	
	private  String LastName;
	
	private LocalDate Date;
	
	private  String titre;
	
	private Map<BlocCompetences,List<Competence>> blocsComp;

	private List<Double> moyenneEtudiant;
	
	private List<Double> moyennePromotion;
	
	private double moyenneGeneraleEtudiant;
	
	private double moyenneGeneralePromotion;

	
	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
		
		
	}



	public Map<BlocCompetences, List<Competence>> getBlocsComp() {
		return blocsComp;
	}



	public void setBlocsComp(Map<BlocCompetences, List<Competence>> blocsComp) {
		this.blocsComp = blocsComp;
	}



	public List<Double> getMoyenneEtudiant() {
		return moyenneEtudiant;
	}



	public void setMoyenneEtudiant(List<Double> moyenneEtudiant) {
		this.moyenneEtudiant = moyenneEtudiant;
	}



	public List<Double> getMoyennePromotion() {
		return moyennePromotion;
	}



	public void setMoyennePromotion(List<Double> moyennePromotion) {
		this.moyennePromotion = moyennePromotion;
	}



	public double getMoyenneGeneraleEtudiant() {
		return moyenneGeneraleEtudiant;
	}



	public void setMoyenneGeneraleEtudiant(double moyenneGeneraleEtudiant) {
		this.moyenneGeneraleEtudiant = moyenneGeneraleEtudiant;
	}



	public double getMoyenneGeneralePromotion() {
		return moyenneGeneralePromotion;
	}



	public void setMoyenneGeneralePromotion(double moyenneGeneralePromotion) {
		this.moyenneGeneralePromotion = moyenneGeneralePromotion;
	}









	


	
	
	

}
