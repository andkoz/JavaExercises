package launcher;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Experiment implements Exercisable {
	final String letters = "abcdefghijklmnopqrstuvwxyz";

	public String getTitle() {
		return "Laboratorium";
	}

	public String getDescription() {
		return "Miejsce zadziwiaj¹cych doœwiadczeñ.";
	}

	public void run() {

		System.out.println("\nLaboratorium z roboczymi kawa³kami.\nZnaki polskie: ¹êœæŸ¿ñ³ó");
		System.out.println("Wybierz:\n1 - pe³noletni/niepe³no/emeryt\n2 - SQL\n3 - sortowanie");
		Scanner func = new Scanner(System.in);
		String option = func.nextLine();
		switch (option) {
		case "1":
			Konsola();
			break;
		case "2":
			sqlExercises();
			break;
		case "3":
			sorting();
			break;
		case "4":
			break;
		}
		func.close();
		System.out.println("\nKoniec laboratorium.");
	}

	private void sorting() {

	
	  int no = 500 ; int width = 23 ;
	  System.out.println("Start.");
	  ArrayList<String> list = generateRandomStringList (no, width) ;
	  System.out.println("Lista losowa:");
	  list.stream().forEach(System.out::println);
	  String[] arr = generateRandomStringArray (no, width) ; 
	  String[] sortedArr = bubbleSortStringArray (arr) ;
	  System.out.println( "Tablica po sortowaniu b¹belkowym:");
	  for (String str : sortedArr) {
		  System.out.println(str);
	  }
	  sortedArr = insertSortStringArray (arr) ;
	  System.out.println("Tablica po sortowaniu przez wstawianie:");
	  for (String str : sortedArr) {
		  System.out.println(str);
	  }
	 return ;
	}

	ArrayList<String> generateRandomStringList(int no, int width) {
		ArrayList<String> list = new ArrayList<String>();
		String str = "";
		int randInt;

		Random rand = new Random();

		if (no < 1 || width < 1)
			return null;

		for (int i = 0; i < no; i++) {
			str = "";
			for (int j = 0; j < width; j++) {
				randInt = rand.nextInt(letters.length());
				str += letters.substring(randInt, randInt + 1);
			}
			list.add(str);
		}
		return list;
	}

	String[] generateRandomStringArray(int no, int width) {
		ArrayList<String> list = new ArrayList<String>();
		// String[] arr = new String[width] ;

		list = generateRandomStringList(no, width);
		String[] arr = list.toArray(new String[] {});
		return arr;
	}

	// sortowanie babelkowe
	String[] bubbleSortStringArray(String[] arr) {
		int no;
		// String str ;

		// mozna by tu zrobic od razu pierwszy przelot, ale kit...
		no = arr.length;
		String str = "";
		do {
			for (int i = 0; i < no - 1; i++) {
				if (arr[i].compareTo(arr[i + 1]) > 0) {
					str = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = str;
				}
			}
			no--;
		} while (no > 1);

		return arr;
	}

	// sortowanie przez wstawianie
	String[] insertSortStringArray(String[] arr) {
		int j, no;
		String str;

		no = arr.length;
		for (int i = 1; i < no; i++) {
			str = arr[i];
			// > Wstaw A[i] w posortowany ci¹g A[1 ... i-1]
			j = i - 1;
			while (j > 0 && arr[j].compareTo(str) > 0) {
				arr[j + 1] = arr[j];
				arr[j] = str;
				j = j - 1;
			}
		}

		return arr;
	}

	private void sqlExercises() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sakila?"
					+ "user=root&password=root&useSSL=false&" + "useUnicode=true&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&serverTimezone=UTC");

			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		System.out.println(conn);

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT title FROM film");

			// or alternatively, if you don't know ahead of time that
			// the query will be a SELECT...

			// if (stmt.execute("SELECT foo FROM bar")) {
			// rs = stmt.getResultSet();
			// }

			// Now do something with the ResultSet ....
			// Array resArr ;
			// rs.absolute(2) ;
			// resArr = rs.getArray(1) ;
			// System.out.println(resArr.toString());
			// Scanner sc = new Scanner (rs.getAsciiStream(1)) ;
			// String str ;
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			//
			// Prepare a call to the stored procedure 'demoSp'
			// with two parameters
			//
			// Notice the use of JDBC-escape syntax ({call ...})
			//

			CallableStatement cStmt = conn.prepareCall("{call demoSp(?, ?)}");

			cStmt.setString(1, "abcdefg");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
	}

	private void Konsola() {

		// stale
		final int WIEK_PELNOLETNI = 18;
		final int WIEK_EMERYTALNY = 65;

		Scanner skaner = new Scanner(System.in);

		System.out.print("Podaj wiek: ");
		int wiek = skaner.nextInt();

		// && - and
		// || - or

		if (wiek < WIEK_PELNOLETNI) {
			System.out.println("Jestes niepelnoletni");
		} else if (wiek >= WIEK_PELNOLETNI && wiek < WIEK_EMERYTALNY) {
			System.out.println("Jestes pelnoletni");
		} else if (wiek >= WIEK_EMERYTALNY) {
			System.out.println("Emeryt");
		}

		skaner.close();

	}

}
