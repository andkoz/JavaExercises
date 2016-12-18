package homework_the_first;

import javax.swing.JOptionPane;

import launcher.Exercisable;

public class Exercise_3_3 implements Exercisable {
	public String getTitle() {
		return "3.3 BMI przez JOptionPane";
	}

	public String getDescription() {
		return "No có¿...";
	}

	public void run() {
		String answer;
		double h = 0;
		double w = 0;

		BMI oByMyself = new BMI();
		do {
			answer = JOptionPane.showInputDialog(null, "Podaj wzrost w metrach:", getTitle(),
					JOptionPane.PLAIN_MESSAGE);
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				h = Double.parseDouble(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((h < 1) || (h > 2.5));
		do {
			answer = JOptionPane.showInputDialog(null, "Podaj wagê w kilogramach:", getTitle(),
					JOptionPane.PLAIN_MESSAGE);
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				w = Double.parseDouble(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((w < 1) || (w > 450));

		if (oByMyself.setData(h, w)) {
			JOptionPane.showMessageDialog(null, oByMyself.getComplexMessage());
		} else {
			JOptionPane.showMessageDialog(null, "Wyst¹pi³ zaplanowany wczeœniej b³¹d #327.");
		}
	}

}
