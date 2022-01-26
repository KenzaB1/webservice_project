package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerModel {
	private int ID;
	private String NAME;
	private String ADDRESS;
	private String PHONENB;

	public CustomerModel() {

	}

	public CustomerModel(String NAME, String ADDRESS, String PHONENB) {
		this.NAME = NAME;
		this.ADDRESS = ADDRESS;
		this.PHONENB = PHONENB;
	}

	public CustomerModel(int ID, String NAME, String ADDRESS, String PHONENB) {
		this.ID = ID;
		this.NAME = NAME;
		this.ADDRESS = ADDRESS;
		this.PHONENB = PHONENB;

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		this.NAME = nAME;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		this.ADDRESS = aDDRESS;
	}

	public String getPHONENB() {
		return PHONENB;
	}

	public void setPHONENB(String pHONENB) {
		this.PHONENB = pHONENB;
	}

}