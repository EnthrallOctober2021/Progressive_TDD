package progressive.qa.data;

public class ZipCodeData {
	
	private String title;
	private String zipCode;
	
	public ZipCodeData(String zipCode, String title) {
		this.zipCode = zipCode;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public String getZipCode() {
		return zipCode;
	}
}
