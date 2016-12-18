package launcher;

import java.awt.EventQueue;

import homework_the_first.Exercise_1_1;
import homework_the_first.Exercise_1_2;
import homework_the_first.Exercise_1_3;
import homework_the_first.Exercise_2_1;
import homework_the_first.Exercise_3_1;
import homework_the_first.Exercise_3_2;
import homework_the_first.Exercise_3_3;
import homework_the_first.Exercise_4_1;
import homework_the_first.Exercise_5_1;
import homework_the_first.Exercise_5_2;
import homework_the_first.Exercise_6_1;
import homework_the_second.Exercise_7_1;

public class Launcher {
	// argumenty wywolania damy do publicznej wiadomosci
	public static String[] args = new String[15];
	// czy aby sie nie przecwiczymy?
	public static final Exercisable[] exercises = { new Experiment(), new Exercise_1_1(), new Exercise_1_2(),
			new Exercise_1_3(), new Exercise_2_1(), new Exercise_3_1(), new Exercise_3_2(), new Exercise_3_3(),
			new Exercise_4_1(), new Exercise_5_1(), new Exercise_5_2(), new Exercise_6_1(), new Exercise_7_1()};

	public static void main(String[] args) throws Exception {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int i = 0;

					for (String arg : args) {
						Launcher.args[i] = arg;
						i++;
					}
					//Thread.sleep(5000);
					new MyGUI();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
