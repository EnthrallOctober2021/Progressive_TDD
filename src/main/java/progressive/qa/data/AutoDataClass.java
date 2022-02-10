package progressive.qa.data;

import progressive.qa.reporting.Logger;

public class AutoDataClass {

	private String expectedTitle1;
	private String firstName;
	private String lastName;
	private String suffix;
	private String dob;
	private String address;
	private String apt;
	private String city;
	
	public AutoDataClass(String expectedTitle, String firstName, String lastName, String suffix, String dob,
			String address, String apt, String city) {
		expectedTitle1 = expectedTitle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.dob = dob;
		this.address = address;
		this.apt = apt;
		this.city = city;
		Logger.log(expectedTitle1);
	}
	
	public String getExpectedTitle() {
		return expectedTitle1;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	public String getApt() {
		return apt;
	}

	public String getCity() {
		return city;
	}

}
