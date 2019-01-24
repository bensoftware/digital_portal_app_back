package com.monetique.model.helper;

import java.io.Serializable;

public class LineClearing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String line;
	int nbLine;
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public int getNbLine() {
		return nbLine;
	}
	public void setNbLine(int nbLine) {
		this.nbLine = nbLine;
	}
	public LineClearing(String line, int nbLine) {
		super();
		this.line = line;
		this.nbLine = nbLine;
	}
	public LineClearing() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
