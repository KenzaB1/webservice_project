package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DelivererModel {
	private int ID;
	private String NAME;
	private String PHONENB;

	public DelivererModel() {

	}

	public DelivererModel(String NAME, String PHONENB) {
		this.NAME = NAME;
		this.PHONENB = PHONENB;

	}

	public DelivererModel(int ID, String NAME, String PHONENB) {
		this.ID = ID;
		this.NAME = NAME;
		this.PHONENB = PHONENB;

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

	public String getPHONENB() {
		return PHONENB;
	}

	public void setPHONENB(String pHONENB) {
		this.PHONENB = pHONENB;
	}

}