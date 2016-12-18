package homework_the_first;

import javax.swing.JOptionPane;

import launcher.Exercisable;

public class Exercise_1_2 implements Exercisable {
	public String getTitle() {
		return "1.2 Interakcja i proste obliczenia";
	}

	public String getDescription() {
		return "Problem warzywniaka w JOptionPane.showInputDialog, " + "czyli ile te� ziemniak�w dzi� zjemy.";
	}

	public void run() {
		String answer;
		float potato1kg = 0;
		float howMany = 0;

		do {
			answer = JOptionPane.showInputDialog("Podaj cen� 1 kg ziemniak�w:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				potato1kg = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((potato1kg < 0.1) || (potato1kg > 10)); // granice rozs�dku dla
															// 1 kg ziemniakow

		// zaokraglimy ew. zbyt dok�adne wartosci
		potato1kg *= 100; // do 2 miejsc po przecinku
		potato1kg = Math.round(potato1kg);
		potato1kg /= 100;

		do {
			answer = JOptionPane
					.showInputDialog("Podaj, prosz� Ciebie,\n" + "ile, mianowicie, kilogram�w chcesz naby�:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				howMany = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((howMany < 0) || (howMany > 65000)); // granice no�no�ci siatki

		JOptionPane.showMessageDialog(null, "Wyobra� sobie,\n" + "�e " + howMany + " kg takich ziemniak�w"
				+ " b�dzie kosztowa� " + String.format("%.02f", potato1kg * howMany) + " z�.");

	}

}
