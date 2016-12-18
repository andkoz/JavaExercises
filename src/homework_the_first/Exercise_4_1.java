package homework_the_first;

import launcher.Exercisable;
import launcher.MyDialog;

public class Exercise_4_1 implements Exercisable {
	public String getTitle() {
		return "4.1 Hotel";
	}

	public String getDescription() {
		return "Zaawansowana logika w ujêciu mikroekonomicznym.";
	}

	public void run() {
		int age, nights;
		double bill;
		Object obj;

		MyDialog dial = new MyDialog();
		obj = dial.acceptValue(MyDialog.Types.INT, getTitle(), "Podaj wiek w latach:");
		if (obj == null)
			return;
		age = (Integer) obj;
		obj = dial.acceptValue(MyDialog.Types.INT, getTitle(), "Podaj liczbê dób hotelowych:");
		if (obj == null)
			return;
		nights = (Integer) obj;

		if (age < 0 || nights < 0) {
			dial.display(MyDialog.MsgTypes.ERR, getTitle(), "Nieprawid³owe dane!");
			return;
		}
		if (age < 18) {
			bill = nights * 100;
		} else {
			switch (nights) {
			case (1):
				bill = 200;
				break;
			case (2):
			case (3):
			case (4):
				bill = nights * 180;
				break;
			default:
				bill = nights * 150;
				break;
			}
		}
		// tu moment kiedy istotne jest po ktorej stronie jest minus
		if (age >= 65)
			bill -= bill * 0.1;
		// przy takich wartosciach nie trzeba zaokraglac do groszy
		// wychodza same zlotowki

		dial.display(MyDialog.MsgTypes.INFO, getTitle(), "Twój rachunek wyniesie " + String.format("%.02f", bill) + " z³.");
	}
}
