package homework_the_second;

import java.text.ParseException;

import launcher.Exercisable;

public class Exercise_7_1 implements Exercisable  {
	public String getTitle() {
		return "7.1 Zarz¹dzanie d³ugami, operacje plikowe";
	}

	public String getDescription() {
		return "Super aplikacja konsolowa";
	}


	public void run() {
		Menu ourMenu = new Menu();
		 try {
			ourMenu.display();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
