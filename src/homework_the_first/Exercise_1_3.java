package homework_the_first;

import javax.swing.JOptionPane;

import launcher.Exercisable;

public class Exercise_1_3 implements Exercisable {
	public String getTitle() {
		return "1.3 Interakcja i proste obliczenia";
	}

	public String getDescription() {
		return "Problem warzywniaka w JOptionPane.showInputDialog, " + "czyli ziemniaki komponuj� si� z bananami.";
	}

	public void run() {
		String answer;
		float potato1kg = 0;
		float howManyPo = 0;
		float banana1kg = 0;
		float howManyBa = 0;

		// ************* DZIAL ZIEMNIAKOW
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
				howManyPo = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((howManyPo < 0) || (howManyPo > 65000)); // granice no�no�ci
															// siatki

		// ************* DZIAL BANANOW
		do {
			answer = JOptionPane.showInputDialog("Podaj cen� 1 kg banan�w:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				banana1kg = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((banana1kg < 0.1) || (banana1kg > 30)); // granice rozs�dku

		// zaokraglimy ew. zbyt dok�adne wartosci
		banana1kg *= 100; // do 2 miejsc po przecinku
		banana1kg = Math.round(banana1kg);
		banana1kg /= 100;

		do {
			answer = JOptionPane
					.showInputDialog("Podaj, prosz� Ciebie,\n" + "ile, mianowicie, kilogram�w chcesz naby�:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				howManyBa = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((howManyBa < 0) || (howManyBa > 65000)); // granice no�no�ci
															// siatki

		// ************* DZIAL PODSUMOWANIA

		answer = "Wi�c tak,\nziemniaki ";
		if (potato1kg * howManyPo == banana1kg * howManyBa) {
			answer = answer + "kosztuj� tyle samo co ";
		} else if (potato1kg * howManyPo > banana1kg * howManyBa) {
			answer = answer + "wynios� Ci� wi�cej ni� ";
		} else {
			answer = answer + "wynios� Ci� mniej ni� ";
		}
		answer = answer + "banany.\nW ka�dym razie w sumie wybulisz" + " "
				+ String.format("%.02f", potato1kg * howManyPo + banana1kg * howManyBa) + " z�.";

		JOptionPane.showMessageDialog(null, answer);

	}

}
