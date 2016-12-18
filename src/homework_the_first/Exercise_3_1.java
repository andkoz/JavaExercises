package homework_the_first;

import launcher.Exercisable;
import launcher.Launcher;

public class Exercise_3_1 implements Exercisable {

	public String getTitle() {
		return "3.1 BMI przez lini� polece�";
	}

	public String getDescription() {
		return "Nie zapomnij ustawi� argument�w wywo�ania programu.";
	}

	public void run() {
		double height, weight;

		// parsujemu command line
		try {
			height = Double.parseDouble(Launcher.args[0]);
		} catch (NumberFormatException e) {
			System.out.println("B��d parametru wywo�ania programu.");
			return; // ?? i co to zrobi?
		}
		try {
			weight = Double.parseDouble(Launcher.args[1]);
		} catch (NumberFormatException e) {
			System.out.println("B��d parametru wywo�ania programu.");
			return; // ?? i co to zrobi?
		}

		System.out
				.println("Wprowadzono nast�puj�ce dane:\n" + "wzrost: " + height + " m\n" + "waga: " + weight + " kg");

		BMI myOwnObj = new BMI();
		if (!myOwnObj.setData(height, weight)) {
			System.out.println("Dane nieprawid�owe.");
			return;
		}

		System.out.println(myOwnObj.getComplexMessage());

	}

}
