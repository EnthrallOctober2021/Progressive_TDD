package testPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFiles {

	public static void main(String[] args) throws Exception {
		//readTextFileBufferedReader("./inputFiles/Fruits.txt");
		//readFileFileReader("./inputFiles/Fruits.txt");
		readFileFileReader("./inputFiles/csvFile.csv");
		System.out.println("System processed");
		
		char a = '1';
		System.out.println((int)a);
	}
	
	public static void readTextFileBufferedReader(String path) throws Exception {
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line;
			while((line = bufferedReader.readLine())!= null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
	public static void readFileFileReader(String path) {
		FileReader fileReader;
		try {
			fileReader = new FileReader(path);
			int i;
			while ((i = fileReader.read()) != -1) {
				System.out.print((char)i);
			}
			System.out.println();
		} catch (FileNotFoundException eFileNotFoundException) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("Input OPutput Exception");
		}
	}
	
	public static void readTextInputStream(String path) throws FileNotFoundException {
		//InputStream inputStream = new FileInputStream(path);
	}
}
