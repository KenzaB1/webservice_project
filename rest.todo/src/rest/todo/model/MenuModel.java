package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuModel {
	private int ID;
	private String RESTOID;
	private String MEALID;

	public MenuModel() {

	}

	public MenuModel(String RESTOID, String MEALID) {
		this.RESTOID = RESTOID;
		this.MEALID = MEALID;

	}

	public MenuModel(int ID, String RESTOID, String MEALID) {
		this.ID = ID;
		this.RESTOID = RESTOID;
		this.MEALID = MEALID;

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getRESTOID() {
		return RESTOID;
	}

	public void setRESTOID(String rESTOID) {
		this.RESTOID = rESTOID;
	}

	public String getMEALID() {
		return MEALID;
	}

	public void setMEALID(String mEALID) {
		this.MEALID = mEALID;
	}

}