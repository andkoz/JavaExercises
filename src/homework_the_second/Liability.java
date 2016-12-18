package homework_the_second;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)

public class Liability implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nextId = 1 ;
	 @XmlElement
	 private int balance = 0 ;  // actual situation
	 @XmlElement
	private int history = 0 ;  // from the begining
	 @XmlElement
	private int loansToBePayed = 0 ;
	private ArrayList<Loan> loans;
	
	public Liability() {
		loans = new ArrayList<>();

	}
	
	public Liability(ArrayList<Loan> loans) {
		this.loans = loans;
		calculateBalance () ;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public void addLoan(Loan newLoan) {
		newLoan.setId(nextId);
		nextId ++ ;
		loans.add(newLoan);
		calculateBalance () ;
	}

	//public void removeLoan(Loan oldLoan) {
		//loans.remove(oldLoan);
		//calculateBalance () ;
	//}
	
	public void removeLoan(int idToRemove) {
		
		// w Javie tego nie wolno robiÄ‡!!!!! -> ConcurrentModificationException
//		for (Produkt zmiennaPetliFor : produkty) {
//			if (produkt.getId() == idProduktuDoUsuniecia) {
//				produkty.remove(produkt);
//			}
//		}
		
		Iterator<Loan> iterator = loans.iterator();
		while (iterator.hasNext()) {
			Loan loan = iterator.next(); 
			// next() pobieramy nastepny obiekt, jednoczesnie przechodzimy na kolejny
			if (loan.getId() == idToRemove) {
				iterator.remove(); // usuwa z kolekcji obiekt ktory ostatnio zwrocil
			}
		}
		calculateBalance();
		
	}

	public void payLoan(int id) {
		for (Loan l : loans) {
			if (l.getId() == id){
				System.out.println("Pozycja: " + l);
				System.out.print("zostala oznaczona jako ");
				if (l.isPayed()){
					l.setPayed(false);
					System.out.println("niesplacona.");
				} else{
					l.setPayed(true);
					System.out.println("splacona.");
					
				}
				calculateBalance () ;
				return ;	
			}
		}
	System.out.println("Nie znaleziono pozycji o podanym identyfikatorze.");
	}
	
	public void displayLoans() {
		for (int i = 0; i < loans.size(); i++) {
			System.out.println( (i+1) + ". " + loans.get(i));
		}
	System.out.println("-------------------------------------------------");
	System.out.print("Wszystkich: " + loans.size() + " Historycznie: " +
						Loan.amountToStr(history, 0));
	System.out.println("  Do sp³aty: " + loansToBePayed + " Bilans: " +
			Loan.amountToStr(balance, 0));
	System.out.println("-------------------------------------------------");
	
	}
	@XmlElements(@XmlElement())
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public int getBalance() {
		return balance;
	}
	public int getHistory() {
		return history;
	}
	public int getLoansToBePayed() {
		return loansToBePayed ;
	}

	private void calculateBalance (){
		history = balance = loansToBePayed = 0 ;
		for (Loan l : loans) {
			history += l.getAmount() ;
			
			if (!l.isPayed()) {
				loansToBePayed ++ ;
				balance += l.getAmount() ;
			}
		}
	}
	
}
