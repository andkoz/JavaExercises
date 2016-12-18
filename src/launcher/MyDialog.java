package launcher;

import javax.swing.JOptionPane;

public class MyDialog {
	// te inty pokrywaja sie z wartosciami zwracanymi
	// przez JOptionPane.showConfirmDialog
	public static final int YES = 0;
	public static final int OK = 0;
	public static final int NO = 1;
	public static final int CANCEL = 2;
	public enum Types {
		INT, SHORT, BYTE, LONG, FLOAT, DOUBLE, STRING
	};
	public enum MsgTypes {
		PLAIN, ERR, INFO, WARN, QUE
	};

	public enum QueTypes {
		YN, YNC, OC
	};


	private Object myParse(Types type, String answer) {
		Object obj = null;
		// switch (type) {
		// a nie chce mi sie zmieniac na kejsy

		if (type == Types.INT) {
			obj = Integer.parseInt(answer);
		} else if (type == Types.SHORT) {
			obj = Short.parseShort(answer);
		} else if (type == Types.BYTE) {
			obj = Byte.parseByte(answer);
		} else if (type == Types.LONG) {
			obj = Long.parseLong(answer);
		} else if (type == Types.FLOAT) {
			obj = Float.parseFloat(answer);
		} else if (type == Types.DOUBLE) {
			obj = Double.parseDouble(answer);
		} else if (type == Types.STRING) {
			// ze stringiem nic nie robimy
			obj = answer;
		}
		return obj;
	}

	public Object acceptValue(Types type, String title, String message) {
		/*
		 * Metoda pobiera dane od uzytkownika przez JOptionPane. Jesli dane nie
		 * daja sie sparsowac do zadanego typu, kaze powtarzac wprowadzanie.
		 * Jesli zostanie nacisniety cancel, zwraca null. Zwraca wartosci typu
		 * Object, aby zachowac uniwersalnosc.
		 */
		Object obj = null;
		String answer;
		boolean done = false;

		// czy done bedzie jeszcze potrzebne?
		done = false;
		do {
			try {
				answer = JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
				if (answer == null)
					return null; // cancel konczy impreze
				obj = myParse(type, answer);
			} catch (NumberFormatException e) {
				continue;
			}
			done = true;
		} while (!done);
		return obj;
	}

	// ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or
	// PLAIN_MESSAGE
	public void display(MsgTypes type, String title, String msg) {
		int internalType = JOptionPane.PLAIN_MESSAGE;

		if (type == MsgTypes.ERR)
			internalType = JOptionPane.ERROR_MESSAGE;
		else if (type == MsgTypes.INFO)
			internalType = JOptionPane.INFORMATION_MESSAGE;
		else if (type == MsgTypes.WARN)
			internalType = JOptionPane.WARNING_MESSAGE;
		else if (type == MsgTypes.QUE)
			internalType = JOptionPane.QUESTION_MESSAGE;

		JOptionPane.showMessageDialog(null, msg, title, internalType);
	}

	public int ask(QueTypes type, String title, String msg) {
		// zwraca 0 - OK i YES, 1 - NO, 2 - CANCEL
		int internalType;

		if (type == QueTypes.YNC)
			internalType = JOptionPane.YES_NO_CANCEL_OPTION;
		else if (type == QueTypes.OC)
			internalType = JOptionPane.OK_CANCEL_OPTION;
		else
			internalType = JOptionPane.YES_NO_OPTION;

		return (JOptionPane.showConfirmDialog(null, msg, title, internalType));
	}

}
