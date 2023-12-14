package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Driver {

	static Service ser = new Service();

	public static void main(String[] args) throws IOException {

		File fofo = new File("fofo.txt");
		ser.fillFile(fofo);
		

		

		

		
	}

}
