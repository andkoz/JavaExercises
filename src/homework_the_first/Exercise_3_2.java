package homework_the_first;

import java.util.Scanner;

import launcher.Exercisable;

public class Exercise_3_2 implements Exercisable, Runnable {
	public String getTitle() {
		return "3.2 BMI przez Scanner";
	}

	public String getDescription() {
		return "Æwiczenie na odczytywanie przez Scanner.";
	}

	public void run() {
		double h, w;

		System.out.println("Podaj wzrost w metrach:");
		Scanner sc = new Scanner(System.in);

		try {
			h = sc.nextDouble();
		} catch (NumberFormatException e) {
			System.out.println("B³¹d. Jaka szkoda...");
			sc.close();
			return;
		}
		System.out.println("Podaj wagê w kilogramach:");
		try {
			w = sc.nextDouble();
		} catch (NumberFormatException e) {
			System.out.println("B³¹d. Jaka szkoda...");
			sc.close();
			return;
		}
		sc.close();

		BMI o = new BMI();
		if (!o.setData(h, w)) {
			System.out.println("System ekspertowy nie zaakceptowa³ tych danych.");
		} else {
			System.out.println(o.getComplexMessage());
		}
	return ;
	}

}
