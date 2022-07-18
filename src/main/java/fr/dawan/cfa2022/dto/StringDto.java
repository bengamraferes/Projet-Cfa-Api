package fr.dawan.cfa2022.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StringDto implements Serializable {

	private String text ;

	
	public StringDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StringDto(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
