package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderModel {
	private int ID;
	private String CUSTOMERID;
	private String DELIVERERID;
	private double TOTALPRICE;

	public OrderModel() {

	}

	public OrderModel(String CUSTOMERID, String DELIVERERID, double TOTALPRICE) {
		this.CUSTOMERID = CUSTOMERID;
		this.DELIVERERID = DELIVERERID;
		this.TOTALPRICE = TOTALPRICE;

	}

	public OrderModel(int ID, String CUSTOMERID, String DELIVERERID, double TOTALPRICE) {
		this.ID = ID;
		this.CUSTOMERID = CUSTOMERID;
		this.DELIVERERID = DELIVERERID;
		this.TOTALPRICE = TOTALPRICE;

	}

	public double getTOTALPRICE() {
		return TOTALPRICE;
	}

	public void setTOTALPRICE(double tOTALPRICE) {
		this.TOTALPRICE = tOTALPRICE;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getCUSTOMERID() {
		return CUSTOMERID;
	}

	public void setCUSTOMERID(String CUSTOMERID) {
		this.CUSTOMERID = CUSTOMERID;
	}

	public String getDELIVERERID() {
		return DELIVERERID;
	}

	public void setDELIVERERID(String DELIVERERID) {
		this.DELIVERERID = DELIVERERID;
	}

}