package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.entities.Product;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the file path: ");   // Getting which file to read
		String strPath = sc.nextLine();

		File file = new File(strPath);

		ArrayList<String> lines = new ArrayList<>();
		ArrayList<String> summary = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) { // Reading File and adding each product to an array
			String line = br.readLine();
			while (line != null) {
				lines.add(line);
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}

		for (String line : lines) { // Separating the array by commas, and inserting the summary of each item into another array
			String productStr[] = line.split(",");
			Product product = new Product(productStr[0], Double.parseDouble(productStr[1]), Integer.parseInt(productStr[2]));
			summary.add(product.getName() + "," + product.Summary());
		}

		boolean success = new File(file.getParent() + "\\out").mkdir(); // Generating a directory for the summary file

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getParent() + "\\out\\summary.csv"))) { // Writing a summary file
			for (String line : summary) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}
}
