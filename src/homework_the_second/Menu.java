package homework_the_second;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import launcher.MyConsole;

public class Menu {
	Scanner scan ;
	public void display() throws ParseException {

		Memorable[] file = {new FilesBinary(), new FilesJson(), new FilesXml(), new FilesZip()};

		String choice;
		
		Liability liability = new Liability ();
		
		try {
			liability = file[0].load();
		} catch (Exception e) {
			System.out.println("Blad odczytu, startujemy z pusta baza");
			e.printStackTrace();
			liability = new Liability();
		}
		

		scan = new Scanner(System.in);

		do {
			int id ;
			System.out.print("Wybierz opcje (0 - podpowiedz): ");
			choice = scan.nextLine();
			//System.out.println(choice);
			switch (choice) {
			case "0":
				System.out.println("0 - Wyswietla ta liste opcji");
				System.out.println("1 - Dodaj pozyczke");
				System.out.println("2 - Zaznacz pozyczke jako splacona/niesplacona");
				System.out.println("3 - Wyswietl liste pozyczek");
				System.out.println("4 - Usun trwale pozycje");
				System.out.println("50 - Zapisz bin | 51 - Zapisz json | 52 - Zapisz xml | 53 - Zapisz zip");
				System.out.println("60 - Odczytaj bin | 61 - Odczytaj json | 62 - Odczytaj xml | 63 - Odczytaj zip");
				System.out.println("70 - Zmien nazwe pliku bin | 71 - json | 72 - xml | 73 - zip");
				System.out.print("Aktualne nazwy to: ");
				for (Memorable f : file){
					System.out.print(" " + f.getFile() + " |");
				}
				System.out.println("");
				System.out.println("q - Koniec programu");
				break ;
			case "1":
				System.out.println("Dodawanie pozyczki");

				System.out.print("Podaj kwote d³ugu: ");
				int amount = scan.nextInt();
				System.out.print("Podaj ident. kontrahenta: ");
				id = scan.nextInt();
				 ;
				Loan newLoan = new Loan(amount, id, dateInput ("Podaj date wpisu: "));
				liability.addLoan(newLoan);
				System.out.print(Loan.amountToStr(amount, 0));
				break;

			case "2":
				liability.displayLoans();
				System.out.println("Podaj id do splaty: ");
				id = scan.nextInt();
				scan.nextLine();
				liability.payLoan(id);
				break;
			case "4":
				liability.displayLoans();
				System.out.println("Podaj id do definitywnego usuniecia: ");
				id = scan.nextInt();
				scan.nextLine();
				liability.removeLoan(id);
				
				break;

			case "3":
				System.out.println("Wyswietlanie listy");
				liability.displayLoans();
				break;
			case "50":
			case "51":
			case "52":
			case "53":
				try {
					file[Integer.parseInt(choice) - 50].save(liability);
					System.out.println("Zapis wykonany poprawnie");
				} catch (Exception e) {
					System.out.println("Blad zapisu do pliku");
					e.printStackTrace();
				}
				break ;
			case "60":
			case "61":
			case "62":
			case "63":
				try {
					liability = file[Integer.parseInt(choice) - 60].load();
				} catch (Exception e) {
					System.out.println("Blad odczytu");
					e.printStackTrace();
				}
				
				
				break ;
			case "70":
			case "71":
			case "72":
			case "73":
				System.out.print("Aktualna nazwa to " + file[Integer.parseInt(choice) - 70].getFile()
						+ ". Podaj nowa (Enter zatwierdza stara): ");
				String dummy = scan.nextLine() ;
				if (dummy.length() > 0) file[Integer.parseInt(choice) - 70].setFile(dummy);
				
				break ;
			case "q":
				/*
				 file = new FilesBinary();
				 
				try {
					file.save(liability);
					System.out.println("Zapis wykonany poprawnie");
				} catch (Exception e) {
					System.out.println("Blad zapisu do pliku");
					e.printStackTrace();
				}
				*/
				System.out.println("Koniec programu");
				break;

			default:
				System.out.println("Podales bledna opcje");
				break;

			}
		} while (!choice.equals("q"));
		scan.close();
	}

	private GregorianCalendar dateInput (String msg){
		
		GregorianCalendar now = new GregorianCalendar();
		int month = now.get(Calendar.MONTH); 
		int year = now.get(Calendar.YEAR);
		int day = now.get(Calendar.DAY_OF_MONTH);
		int dummy ;
		
		System.out.println(msg);
		System.out.print("Dzien (" + day + "): ");
		try{
			dummy = Integer.parseInt(scan.nextLine()) ;
			day = dummy ;
		}catch(NumberFormatException e){}
		System.out.print(" Miesiac (" + (month+1) + "): ");
		try{
			dummy = Integer.parseInt(scan.nextLine()) ;
			month = dummy - 1;
		}catch(NumberFormatException e){}
		System.out.print(" Rok (" + year + "): ");
		try{
			dummy = Integer.parseInt(scan.nextLine()) ;
			year = dummy ;
		}catch(NumberFormatException e){}
		
		
		return new GregorianCalendar(year, month, day);
	}
}
