package homework_the_first;

import javax.swing.JOptionPane;

import launcher.Exercisable;

public class Exercise_2_1 implements Exercisable {
	public String getTitle() {
		return "2.1 Buty do szewca";
	}

	public String getDescription() {
		return "Problem obuwniczy w ujêciu tygodniowym";
	}

	public void run() {
		String[] possibleValues = { "poniedzia³ek", "wtorek", "œroda", "czwartek", "pi¹tek", "sobota", "niedziela" };
		int dayOfWeekInt = 1;
		String answer;
		int days = 0;

		Object selectedValue = JOptionPane.showInputDialog(null, "Kiedy buty posz³y do szewca?", getTitle(),
				JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);

		if (selectedValue == null)
			return; // bez laski - z Bogiem

		for (String dayOfWeek : possibleValues) { // taki "for" przydatny,
													// gdybysmy nie wiedzieli
			if (dayOfWeek.equals(selectedValue)) { // ile opcji jest w tablicy
				break;
			}
			dayOfWeekInt++;
		}

		do {
			answer = JOptionPane.showInputDialog(null, "Ile dni potrwa naprawa?", getTitle(),
					JOptionPane.QUESTION_MESSAGE);
			if (answer == null)
				return; // cancel konczy impreze
			// nie damy se wcisnac byle czego:
			try {
				days = Integer.parseInt(answer);
			} catch (NumberFormatException e) {
				continue;
			}
		} while ((days < 0) || (days > 100000));

		// liczymy, ze szefc robi 7 dni w tygodniu
		days = Math.floorMod(days, 7);
		if (days + dayOfWeekInt > 7) {
			dayOfWeekInt = days + dayOfWeekInt - 7;
		} else {
			dayOfWeekInt = days + dayOfWeekInt;
		}

		JOptionPane.showMessageDialog(null, "Dzieñ odbioru butów to " + possibleValues[dayOfWeekInt - 1] + ".");

	}
}
