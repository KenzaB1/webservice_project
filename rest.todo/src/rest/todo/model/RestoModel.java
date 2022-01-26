package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestoModel {
	private int ID;
	private String NAME;
	private String ADDRESS;
	private String TYPE;
	private String DESCRIPTION;

	public RestoModel() {

	}

	public RestoModel(String NAME, String ADDRESS, String TYPE, String DESCRIPTION) {

		this.NAME = NAME;
		this.ADDRESS = ADDRESS;
		this.TYPE = TYPE;
		this.DESCRIPTION = DESCRIPTION;

	}

	public RestoModel(int ID, String NAME, String ADDRESS, String TYPE, String DESCRIPTION) {
		this.ID = ID;
		this.NAME = NAME;
		this.ADDRESS = ADDRESS;
		this.TYPE = TYPE;
		this.DESCRIPTION = DESCRIPTION;

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

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String DESCRIPTION) {
		this.DESCRIPTION = DESCRIPTION;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

}