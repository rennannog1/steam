package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

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
					String[] fields = line.split(",");
					list.add(new Product(fields[0], Double.parseDouble(fields[1])));
					line = br.readLine();
				}

				double avg = list.stream().map(p -> p.getPrice()).reduce(0.0, (x, y) -> x + y) / list.size();

				System.out.println("Average Price: " + String.format("%.2f", avg));
				
				list.removeIf(p -> p.getPrice()>avg);
				list.sort(new MyComparator().reversed());
				List<String> name = list.stream().map(p->p.getName()).collect(Collectors.toList());
				name.forEach(System.out::println);
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
}