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
 * Zarz¹dzanie d³ugami Drodzy Kursanci, prawie ka¿demu z nas zdarza siê po¿yczaæ
 * od kogoœ lub komuœ pieni¹dze. Czêsto zapominamy o tym czy uregulowaliœmy
 * nale¿noœæ lub czy ktoœ ju¿ odda³ nam nasze pieni¹dze. W ramach drugiej pracy
 * domowej napiszecie aplikacje, która bêdzie mog³a pe³niæ rolê waszej prywatnej
 * ewidencji d³ugów. Aplikacja powinna posiadaæ nastêpuj¹ce funkcje: • dodanie
 * nowego d³ugu • usuniêcie d³ugu • wyœwietlenie listy wszystkich d³ugów •
 * wyœwietlenie listy d³ugów spe³niaj¹cych jedno z kryteriów (do wyboru): -
 * tylko te, gdzie my jesteœmy d³u¿nikiem - tylko te, gdzie my jesteœmy
 * wierzycielem - tylko sp³acone - tylko niesp³acone • „sp³acenie” d³ugu - czyli
 * oznaczenie d³ugu jako sp³aconego • zapamiêtanie swojego stanu w sposób trwa³y
 * (do wyboru plik binarny, plik tekstowy) Aplikacja powinna komunikowaæ siê
 * u¿ytkownikiem wyœwietlaj¹c w konsoli proste menu u¿ytkownika (czyli tak jak
 * to robiliœmy na zajêciach). Aplikacja powinna pozwalaæ na wprowadzenie
 * zarówno d³ugu, który my musimy sp³aciæ, jak i takiego, gdzie to ktoœ inny
 * jest nam winny pieni¹dze. Lista czynnoœci, które przybli¿¹ was do celu: 1.
 * Utwórz nowy projekt (mo¿na od razu wybraæ Maven Project, jeœli wiemy, ¿e
 * bêdziemy korzystaæ z zewnêtrznych bibliotek) :) 2. Zastanów siê jakie klasy
 * bêd¹ potrzebne w naszej aplikacji. 3. Utwórz odpowiednie klasy, zdefiniuj dla
 * nich sk³adowe i metody. Pamiêtaj, ¿e ka¿da sk³adowa powinna mieæ typ oraz
 * nazwê (sam/sama dobierz odpowiedni typ danych). Pamiêtaj, ¿e ka¿da metoda
 * musi mieæ typ zwracany, nazwê oraz listê parametrów. Pamiêtaj równie¿ o
 * zasadzie hermetyzacji - klasy nie powinny udostêpniaæ na zewn¹trz swoich
 * sk³adowych.
 *  Pomyœl równie¿ o stworzeniu odpowiednich konstruktorów, a tak¿e o
 * nadpisaniu metod toString, hashCode, equals jeœli bêdzie to potrzebne. 4. Nie
 * przechodŸ dalej, jeœli w projekcie s¹ b³êdy, po wykonaniu kroku 3 œrodowisko
 * programistyczne nie powinno wyœwietlaæ ¿adnych czerwonych krzy¿yków. Strona (
 * z ( 1 2 5. Utwórz za pomoc¹ kombinacji pêtli do-while oraz instrukcji switch
 * menu u¿ytkownika. Pamiêtaj, ¿e nie musisz od razu dodawaæ wszystkich opcji,
 * mo¿esz to zrobiæ póŸniej. Proponuje zacz¹æ od „dodaj nowy d³ug” i „wyœwietl
 * listê wszystkich d³ugów”. 6. W Menu dopisz instrukcje pozwalaj¹ce pobraæ od
 * u¿ytkownika odpowiednie dane do utworzenia d³ugu, a potem analogicznie
 * zaimplementuj pozosta³e funkcje. 7. Kiedy stwierdzisz, ¿e aplikacja poprawnie
 * wczytuje dane o d³ugach, usuwa d³ugi, pozwala oznaczyæ d³ug jako sp³acony
 * oraz wyœwietla listê d³ugów mo¿esz przejœæ do implementacji zapisu danych do
 * pliku.
 *  8. Stwórz klasê, która bêdzie posiada³a metody zapisz oraz wczytaj i
 * za pomoc¹ wybranego przez siebie sposobu zaimplementuj zapisz i odczyt pliku.
 * Jeœli chcesz skorzystaæ z biblioteki zewnêtrznej skonwertuj swój projekt na
 * Maven Project (jeœli na pocz¹tku wybra³eœ/³aœ standardowy projekt). 9.
 * Wype³nij ankietê na temat tego zadania: http://bit.ly/02-ZadanieDomowe Punkty
 * bonusowe dla chêtnych: - zaimplementuj przynajmniej dwa sposoby zapisu do
 * pliku i zapytaj u¿ytkownika, z którego sposobu chce skorzystaæ -
 * zaimplementuj zapis i odczyt do pliku XML - pozwól u¿ytkownikowi wybraæ nazwê
 * pliku, do którego program zapisuje dane lub z którego je czyt
 * 
 */
