package homework_the_first;

import javax.swing.JOptionPane;

import launcher.Exercisable;

public class Exercise_1_3 implements Exercisable {
	public String getTitle() {
		return "1.3 Interakcja i proste obliczenia";
	}

	public String getDescription() {
		return "Problem warzywniaka w JOptionPane.showInputDialog, " + "czyli ziemniaki komponuj¹ siê z bananami.";
	}

	public void run() {
		String answer;
		float potato1kg = 0;
		float howManyPo = 0;
		float banana1kg = 0;
		float howManyBa = 0;

		// ************* DZIAL ZIEMNIAKOW
		do {
			answer = JOptionPane.showInputDialog("Podaj cenê 1 kg ziemniaków:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				potato1kg = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((potato1kg < 0.1) || (potato1kg > 10)); // granice rozs¹dku dla
															// 1 kg ziemniakow

		// zaokraglimy ew. zbyt dok³adne wartosci
		potato1kg *= 100; // do 2 miejsc po przecinku
		potato1kg = Math.round(potato1kg);
		potato1kg /= 100;

		do {
			answer = JOptionPane
					.showInputDialog("Podaj, proszê Ciebie,\n" + "ile, mianowicie, kilogramów chcesz nabyæ:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				howManyPo = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((howManyPo < 0) || (howManyPo > 65000)); // granice noœnoœci
															// siatki

		// ************* DZIAL BANANOW
		do {
			answer = JOptionPane.showInputDialog("Podaj cenê 1 kg bananów:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				banana1kg = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((banana1kg < 0.1) || (banana1kg > 30)); // granice rozs¹dku

		// zaokraglimy ew. zbyt dok³adne wartosci
		banana1kg *= 100; // do 2 miejsc po przecinku
		banana1kg = Math.round(banana1kg);
		banana1kg /= 100;

		do {
			answer = JOptionPane
					.showInputDialog("Podaj, proszê Ciebie,\n" + "ile, mianowicie, kilogramów chcesz nabyæ:");
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				howManyBa = Float.parseFloat(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((howManyBa < 0) || (howManyBa > 65000)); // granice noœnoœci
															// siatki

		// ************* DZIAL PODSUMOWANIA

		answer = "Wiêc tak,\nziemniaki ";
		if (potato1kg * howManyPo == banana1kg * howManyBa) {
			answer = answer + "kosztuj¹ tyle samo co ";
		} else if (potato1kg * howManyPo > banana1kg * howManyBa) {
			answer = answer + "wynios¹ Ciê wiêcej ni¿ ";
		} else {
			answer = answer + "wynios¹ Ciê mniej ni¿ ";
		}
		answer = answer + "banany.\nW ka¿dym razie w sumie wybulisz" + " "
				+ String.format("%.02f", potato1kg * howManyPo + banana1kg * howManyBa) + " z³.";

		JOptionPane.showMessageDialog(null, answer);

	}

}
