package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlatModel {
	private int ID;
	private String NAME;
	private double PRICE;

	public PlatModel() {

	}

	public PlatModel(String NAME, double PRICE) {
		this.NAME = NAME;
		this.PRICE = PRICE;

	}

	public PlatModel(int ID, String NAME, double PRICE) {
		this.ID = ID;
		this.NAME = NAME;
		this.PRICE = PRICE;

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		this.NAME = nAME;
	}

	public double getPRICE() {
		return PRICE;
	}

	public void setPRICE(double PRICE) {
		this.PRICE = PRICE;
	}

}