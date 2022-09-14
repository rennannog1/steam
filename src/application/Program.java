package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter file full path: ");
			String path = sc.nextLine();
			try (BufferedReader br = new BufferedReader(new FileReader(path))) {

				String line = br.readLine();
				while (line != null) {
					String [] fields = line.split(",");
					list.add(new Product (fields[0],Double.parseDouble(fields[1])));
					line = br.readLine();
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println(Arrays.toString(list.toArray()));
	}
}