package homework_the_first;

import java.util.ArrayList;
import java.util.Random;

import launcher.Exercisable;
import launcher.MyDialog;

public class Exercise_6_1 implements Exercisable {
	public String getTitle() {
		return "6.1 Æwiczenia na pêtle i tablice";
	}

	public String getDescription() {
		return "Ojojoj! Tu siê tyle dzieje, ¿e g³owa ma³a...";
	}

	final private String tit = getTitle();

	public void run() {
		Integer numOfItems = 0;
		Object obj;

		ArrayList<Integer> file = new ArrayList<Integer>();
		MyDialog dial = new MyDialog();

		numOfItems = fillList(file);
		if (numOfItems == null) {
			dial.display(MyDialog.MsgTypes.WARN, tit, "Przerwano na ¿yczenie u¿ytkownika.");
			return;
		}

		Integer[] fileArr = file.toArray(new Integer[] {});
		// automatyczne przepisanie listy do tablicy Iteger[] nie pozwala
		// wykorzystac tej tablicy w metodach potrzebujacych int[]
		// wiec zrobimy sobie tablice int[] recznie
		int[] intArr = new int[numOfItems];
		for (int i = 0; i < numOfItems; i++) {
			intArr[i] = fileArr[i];
		}
		dial.display(MyDialog.MsgTypes.INFO, tit,
				"Metody wykonane na liœcie\n" + "daj¹ nam takie wyniki:\nSuma: " + suma(file) + "\nŒrednia: "
						+ srednia(file) + "\nMax: " + max(file) + "\nMin: " + min(file) + "\n|Max-Min|: "
						+ roznicaMinMax(file) + "\n A metody wykonane na tablicy\n" + "daj¹ nam takie:\nSuma: "
						+ sumaArr(intArr) + "\nŒrednia: " + sredniaArr(intArr) + "\nMax: " + maxArr(intArr) + "\nMin: "
						+ minArr(intArr) + "\n|Max-Min|: " + roznicaMinMaxArr(intArr));

		obj = dial.acceptValue(MyDialog.Types.INT, tit, "Jeszcze jedna proœba.\n" + "Podaj jak¹œ liczbê ca³kowit¹:");
		if (obj == null)
			return;
		int divider = (Integer) obj;

		dial.display(MyDialog.MsgTypes.INFO, tit,
				"A teraz spójrz na konsolê, gdy¿ " + "kolejne\næwiczenie tam w³aœnie wypluje dane.\nGotów?");
		wypiszPodzielne(intArr, divider);

		dial.display(MyDialog.MsgTypes.QUE, tit, "Dobra, jeszcze tylko wyœwietlê wyniki\n" + "trzech ostatnich metod æwiczeniowych, ok?");
		dial.display(MyDialog.MsgTypes.INFO, tit,
				"Oto dwie z nich, mianowicie:\n" + "Integer pierwszaPodzielna(int[] file, int x): "
						+ pierwszaPodzielna(intArr, divider) + "\nint ileWiekszych(int[] file, int x): "
						+ ileWiekszych(intArr, divider));
		dial.display(MyDialog.MsgTypes.INFO, tit,
				"Ale ¿eby wyœwietliæ wynik ostatniej, " + "trzeba zorganizowaæ jeszcze jedn¹ tablicê, wiêc...");

		ArrayList<Integer> file2 = new ArrayList<Integer>();
		Integer numOfItems2 = fillList(file2);
		if (numOfItems2 == null) {
			dial.display(MyDialog.MsgTypes.WARN, tit, "Przerwano na ¿yczenie u¿ytkownika.");
			return;
		}
		int[] intArr2 = new int[numOfItems2];
		for (int i = 0; i < numOfItems2; i++) {
			intArr2[i] = file2.get(i);
		}
		dial.display(MyDialog.MsgTypes.INFO, tit,
				"I teraz maj¹c ju¿ dwie tablice" + "\nmo¿na wyœwietliæ wynik dzia³ania ostatniej metody, tzn.\n"
						+ "Integer znajdzWspolny(int[] t2, int[] t1): " + znajdzWspolny(intArr, intArr2)
						+ "\nUff... To tyle.");
	}

	private Integer fillList(ArrayList<Integer> file) {
		int numOfItems = 0;
		String str = "";
		Object obj;
		MyDialog dial = new MyDialog();
		if (dial.ask(MyDialog.QueTypes.YN, tit, "Chcesz wprowadzaæ dane samodzielnie?") == launcher.MyDialog.NO) {

			obj = dial.acceptValue(MyDialog.Types.INT, tit, "Dobrze.\nW takim razie program sam wygeneruje"
					+ "\nzadan¹ iloœæ losowych liczb.\nPodaj liczbê 0 - 32000:");
			if (obj == null)
				return null;

			numOfItems = (Integer) obj;
			if (numOfItems < 0 || numOfItems > 32000) {
				dial.display(MyDialog.MsgTypes.ERR, tit, "Liczba spoza zakresu!");
				return null;
			}

			Random rand = new Random();
			for (int i = 0; i < numOfItems; i++) {
				file.add(rand.nextInt());
				str = str + "\n" + file.get(i);
			}

		} else {
			while ((obj = dial.acceptValue(MyDialog.Types.INT, tit, "Podaj kolejn¹ liczbê ca³kowit¹\n" + "lub wciœnij Cancel, aby "
					+ "zakoñczyæ wprowadzanie.")) != null) {
				file.add((Integer) obj);
				str = str + "\n" + file.get(numOfItems);
				numOfItems++;
			}

		}
		if (numOfItems < 51)
			dial.display(MyDialog.MsgTypes.INFO, tit, "Oto nasza lista:" + str);
		return numOfItems;
	}

	static long suma(ArrayList<Integer> file) {
		long sum = 0L;
		for (int i : file) {
			sum += (long) i;
		}
		return sum;
	}

	static double srednia(ArrayList<Integer> file) {
		long sum = 0L;
		int noItems = 0;

		noItems = file.size();
		if (noItems == 0)
			return 0;
		sum = suma(file);

		return sum / (double) noItems;
	}

	static int max(ArrayList<Integer> file) {
		if (file.size() == 0)
			return 0;
		file.sort(null);
		return file.get(file.size() - 1);
	}

	static int min(ArrayList<Integer> file) {
		if (file.size() == 0)
			return 0;
		file.sort(null);
		return file.get(0);
	}

	static long roznicaMinMax(ArrayList<Integer> file) {

		if (file.size() == 0)
			return 0;
		return Math.abs((long) max(file) - (long) min(file));
	}

	static long sumaArr(int[] file) {
		long sum = 0L;
		for (int i : file) {
			sum += (long) i;
		}
		return sum;
	}

	static double sredniaArr(int[] file) {
		long sum = 0L;
		int noItems = 0;

		noItems = file.length;
		if (noItems == 0)
			return 0;
		sum = sumaArr(file);
		return sum / (double) noItems;
	}

	static int maxArr(int[] file) {
		int max = Integer.MIN_VALUE;

		if (file.length == 0)
			return 0;

		for (int i : file) {
			if (max < i)
				max = i;
		}
		return max;
	}

	static int minArr(int[] file) {
		int min = Integer.MAX_VALUE;

		if (file.length == 0)
			return 0;

		for (int i : file) {
			if (min > i)
				min = i;
		}
		return min;
	}

	static long roznicaMinMaxArr(int[] file) {
		if (file.length == 0)
			return 0;
		return Math.abs((long) maxArr(file) - (long) minArr(file));
	}

	void wypiszPodzielne(int[] file, int x) {
		if (file.length == 0)
			return;
		System.out.println("Liczby z tablicy podzielne przez " + x + ":");
		for (int i : file) {
			if (i % x == 0) {
				System.out.println(i);
			}
		}
	}

	Integer pierwszaPodzielna(int[] file, int x) {
		for (int i : file) {
			if (i % x == 0) {
				return i;
			}
		}
		return null;
	}

	int ileWiekszych(int[] file, int x) {
		int howMany = 0;
		for (int i : file) {
			if (i > x) {
				howMany++;
			}
		}
		return howMany;
	}

	Integer znajdzWspolny(int[] file, int[] tab) {
		for (int i : file) {
			for (int j : tab) {
				if (i == j) {
					return i;
				}
			}
		}
		return null;
	}
}
