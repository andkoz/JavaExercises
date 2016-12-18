package homework_the_first;

import javax.swing.JOptionPane;

import launcher.Exercisable;

public class Exercise_1_1 implements Exercisable {
	public String getTitle() {
		return "1.1 Interakcja i proste obliczenia";
	}

	public String getDescription() {
		return "Problem warzywniaka w JOptionPane.showInputDialog";
	}

	public void run() {
		String answer;
		float potato1kg = 0;

		do {
			answer = JOptionPane.showInputDialog(null, "Podaj cen� 1 kg ziemniak�w:", getTitle(),
					JOptionPane.PLAIN_MESSAGE);
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

		JOptionPane.showMessageDialog(null, "Wyobra� sobie, �e pi�� kilogram�w takich ziemniak�w" + " b�dzie kosztowa� "
				+ String.format("%.02f", potato1kg * 5) + " z�.");

	}
}
