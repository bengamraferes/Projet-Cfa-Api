package fr.dawan.cfa2022.dto;

import java.io.Serializable;
@SuppressWarnings("serial")
public class EvaluationDto implements Serializable {

	
	private long id;
	

	private long epreuveId;
	
	
	private long etudiantId;
	
	private double note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public long getEpreuveId() {
		return epreuveId;
	}

	public void setEpreuveId(long epreuveId) {
		this.epreuveId = epreuveId;
	}

	public long getEtudiantId() {
		return etudiantId;
	}

	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
	
	
}
