package fr.epita.northwind.datamodel;

public class Shipper {

	private long id;
	private String companyName;
	private String phoneNumber;

	public Shipper(long id, String companyName, String phoneNumber) {
		this.id = id;
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
	}

	public Shipper() {

	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
