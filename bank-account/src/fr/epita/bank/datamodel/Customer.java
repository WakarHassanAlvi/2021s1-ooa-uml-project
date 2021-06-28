package fr.epita.bank.datamodel;

public class Customer {

	private String name;
	private String address;

	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.startsWith("1")){
			System.out.println("invalid value");

		}else {
			this.name = name;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
