package homework_the_second;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;

public class Loan implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final GregorianCalendar date = new GregorianCalendar(); 
	private int id = 0 ;
	private int amount = 0; // unit -> cent
	private boolean payed = false;
	private int contractorId = 0 ;
	private String strDate = dateToStr(Loan.date) ;
	
	public Loan() {
		super();
	}

	public Loan(int amount) {
		super();
		this.amount = amount;
		payed = false ;
	}
	public Loan(int amount, int contId) {
		super();
		contractorId = contId ;
		this.amount = amount;
		payed = false ;
		strDate = dateToStr(date) ;
	}
	public Loan(int amount, int contId, GregorianCalendar date) {
		super();
		contractorId = contId ;
		this.amount = amount;
		if (date == null){
			strDate = dateToStr(Loan.date);
		} else {
			strDate = dateToStr(date);
		}
		payed = false ;
	}
	public Loan(int amount, int contId, String strDate) {
		super();
		contractorId = contId ;
		this.amount = amount;
		this.strDate = strDate ;
		payed = false ;
	}


	public static String amountToStr (int amount, int digits){
		String space = "                                          " ;
		String str = String.format("%d.%02d", amount/100, Math.abs(amount%100)) ;
		if (digits != 0){
			space = space.substring(0, digits - str.length()) ;
		} else{
			space = "" ;
		}
		return space + str ;
	}
	public static String dateToStr (GregorianCalendar date){
		return String.format("%4d", date.get(Calendar.YEAR)) + "." +
				String.format("%02d", date.get(Calendar.MONTH)+1) + "." +
				String.format("%02d", date.get(Calendar.DAY_OF_MONTH))	;
		
	}
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getContractorId() {
		return contractorId;
	}

	public void setContractorId(int contractorId) {
		this.contractorId = contractorId;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

/*
	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
*/
	@Override
	public String toString() {
		return "Id.: " + String.format("%3d", id) + " [kwota= " + amountToStr (amount, 12) + /*", debt=" + debt + */", "
				+ "splacona= " + (payed ? "tak" : "nie") + " kontrahent: " + contractorId + 
				" data: " + strDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		//result = prime * result + (debt ? 1231 : 1237);
		result = prime * result + (payed ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (amount != other.amount)
			return false;
		//if (debt != other.debt)
			//return false;
		if (payed != other.payed)
			return false;
		return true;
	}


}

/*
 * Zarz�dzanie d�ugami Drodzy Kursanci, prawie ka�demu z nas zdarza si� po�ycza�
 * od kogo� lub komu� pieni�dze. Cz�sto zapominamy o tym czy uregulowali�my
 * nale�no�� lub czy kto� ju� odda� nam nasze pieni�dze. W ramach drugiej pracy
 * domowej napiszecie aplikacje, kt�ra b�dzie mog�a pe�ni� rol� waszej prywatnej
 * ewidencji d�ug�w. Aplikacja powinna posiada� nast�puj�ce funkcje: � dodanie
 * nowego d�ugu � usuni�cie d�ugu � wy�wietlenie listy wszystkich d�ug�w �
 * wy�wietlenie listy d�ug�w spe�niaj�cych jedno z kryteri�w (do wyboru): -
 * tylko te, gdzie my jeste�my d�u�nikiem - tylko te, gdzie my jeste�my
 * wierzycielem - tylko sp�acone - tylko niesp�acone � �sp�acenie� d�ugu - czyli
 * oznaczenie d�ugu jako sp�aconego � zapami�tanie swojego stanu w spos�b trwa�y
 * (do wyboru plik binarny, plik tekstowy) Aplikacja powinna komunikowa� si�
 * u�ytkownikiem wy�wietlaj�c w konsoli proste menu u�ytkownika (czyli tak jak
 * to robili�my na zaj�ciach). Aplikacja powinna pozwala� na wprowadzenie
 * zar�wno d�ugu, kt�ry my musimy sp�aci�, jak i takiego, gdzie to kto� inny
 * jest nam winny pieni�dze. Lista czynno�ci, kt�re przybli�� was do celu: 1.
 * Utw�rz nowy projekt (mo�na od razu wybra� Maven Project, je�li wiemy, �e
 * b�dziemy korzysta� z zewn�trznych bibliotek) :) 2. Zastan�w si� jakie klasy
 * b�d� potrzebne w naszej aplikacji. 3. Utw�rz odpowiednie klasy, zdefiniuj dla
 * nich sk�adowe i metody. Pami�taj, �e ka�da sk�adowa powinna mie� typ oraz
 * nazw� (sam/sama dobierz odpowiedni typ danych). Pami�taj, �e ka�da metoda
 * musi mie� typ zwracany, nazw� oraz list� parametr�w. Pami�taj r�wnie� o
 * zasadzie hermetyzacji - klasy nie powinny udost�pnia� na zewn�trz swoich
 * sk�adowych.
 *  Pomy�l r�wnie� o stworzeniu odpowiednich konstruktor�w, a tak�e o
 * nadpisaniu metod toString, hashCode, equals je�li b�dzie to potrzebne. 4. Nie
 * przechod� dalej, je�li w projekcie s� b��dy, po wykonaniu kroku 3 �rodowisko
 * programistyczne nie powinno wy�wietla� �adnych czerwonych krzy�yk�w. Strona (
 * z ( 1 2 5. Utw�rz za pomoc� kombinacji p�tli do-while oraz instrukcji switch
 * menu u�ytkownika. Pami�taj, �e nie musisz od razu dodawa� wszystkich opcji,
 * mo�esz to zrobi� p�niej. Proponuje zacz�� od �dodaj nowy d�ug� i �wy�wietl
 * list� wszystkich d�ug�w�. 6. W Menu dopisz instrukcje pozwalaj�ce pobra� od
 * u�ytkownika odpowiednie dane do utworzenia d�ugu, a potem analogicznie
 * zaimplementuj pozosta�e funkcje. 7. Kiedy stwierdzisz, �e aplikacja poprawnie
 * wczytuje dane o d�ugach, usuwa d�ugi, pozwala oznaczy� d�ug jako sp�acony
 * oraz wy�wietla list� d�ug�w mo�esz przej�� do implementacji zapisu danych do
 * pliku.
 *  8. Stw�rz klas�, kt�ra b�dzie posiada�a metody zapisz oraz wczytaj i
 * za pomoc� wybranego przez siebie sposobu zaimplementuj zapisz i odczyt pliku.
 * Je�li chcesz skorzysta� z biblioteki zewn�trznej skonwertuj sw�j projekt na
 * Maven Project (je�li na pocz�tku wybra�e�/�a� standardowy projekt). 9.
 * Wype�nij ankiet� na temat tego zadania: http://bit.ly/02-ZadanieDomowe Punkty
 * bonusowe dla ch�tnych: - zaimplementuj przynajmniej dwa sposoby zapisu do
 * pliku i zapytaj u�ytkownika, z kt�rego sposobu chce skorzysta� -
 * zaimplementuj zapis i odczyt do pliku XML - pozw�l u�ytkownikowi wybra� nazw�
 * pliku, do kt�rego program zapisuje dane lub z kt�rego je czyt
 * 
 */
