package MyFirstWebApps;

public class Student {
	private int id;
	private String firstname;
	private String lastname;
	private String streetaddress;
	private String postcode;
	private String postoffice;

	public Student() {
		id = 0;
		firstname = "";
		lastname = "";
		streetaddress = "";
		postcode = "";
		postoffice = "";
	}

	public Student(int id, String firstname, String lastname, String streetaddress, String postcode, String postoffice) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.postcode = postcode;
		this.postoffice = postoffice;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String name) {
		this.firstname = name;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String name) {
		this.lastname = name;
	}
	
	public String getAddress() {
		return streetaddress;
	}

	public void setAddress(String address) {
		this.streetaddress = address;
	}
	
	public String getPostCode() {
		return postcode;
	}

	public void setPostCode(String code) {
		this.postcode = code;
	}
	
	public String getPostOffice() {
		return postoffice;
	}

	public void setPostOffice(String office) {
		this.postoffice = office;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + firstname + " " + lastname + ", address=" + streetaddress + ", " + postcode + postoffice + "]";
	}
}
