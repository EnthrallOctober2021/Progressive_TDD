package testPackage;

import progressive.qa.utilities.Configurable;

public class TestConfig {

	public static void main(String[] args) {
		//Configurable configurableX = new Configurable();
		Configurable configurable = Configurable.getInstance();
		configurable.getUrl();
		Configurable configurable2 = Configurable.getInstance();
		System.out.println(configurable.hashCode());
		System.out.println(configurable2.hashCode());
	}
}
