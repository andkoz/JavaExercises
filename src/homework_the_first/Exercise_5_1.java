package homework_the_first;


import launcher.Exercisable;
import launcher.MyDialog;

public class Exercise_5_1 implements Exercisable {
	public String getTitle() {
		return "5.1 Tr�jk�t i jego area�";
	}

	public String getDescription() {
		return "Obliczanie pola powierzchni tr�jk�ta ze wzoru Herona.";
	}

	public void run() {
		double[] sides = { 0, 0, 0 };
		Object obj;

		MyDialog dial = new MyDialog();
		for (int i = 0; i < 3; i++) {
			obj = dial.acceptValue(MyDialog.Types.DOUBLE, getTitle(), "Podaj d�ugo�� " + (i + 1) + " boku:");
			if (obj == null)
				return; // wcisnieto cancel
			sides[i] = (Double) obj;
		}
		Triangle t = new Triangle(sides[0], sides[1], sides[2]);
		if (t.getArea() == 0) {
			dial.display(MyDialog.MsgTypes.WARN, getTitle(),
					"Boki o wymiarach:\n" + sides[0] + "\n" + sides[1] + "\n" + sides[2] + "\nnie utworz� tr�jk�ta.");
			return;
		}
		dial.display(MyDialog.MsgTypes.INFO, getTitle(), "Pole tr�jk�ta o wymiarach\n" + t.getA() + "\n" + t.getB() + "\n" + t.getC()
				+ "\nwynosi: " + t.getArea());

	}

}
