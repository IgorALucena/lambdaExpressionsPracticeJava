package program;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employe;

public class Apliccation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		Double salary = sc.nextDouble();
		List<Employe> list = new ArrayList<Employe>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while(line != null) {
				String[] content = line.split(",");
				list.add(new Employe(content[0], content[1], Double.parseDouble(content[2])));
				line = br.readLine();
			}
			List<String> emails = list.stream()
					.filter(x -> x.getSalary()>=2000.0)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			emails.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(n -> n.getName().charAt(0) == 'M')
					.map(s -> s.getSalary())
					.reduce(0.0, (x,y) -> x+y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
		}
		catch(IOException err) {
			System.out.println(err);
		}
		
		sc.close();	
	}
}
