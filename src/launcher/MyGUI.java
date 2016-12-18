/*
 * Mamy tutaj problem: anonimowe klasy wewnetrzne - tak, czy nie.
 * Przy dodawaniu elementow GUI mozna by od razu dac im wewnetrzne
 * listenery z metodami actionPerformed.
 * Na teraz bardziej porzadna wydaje sie jedna osobna metoda
 * actionPerformed. Jednak rodzi to klopot z przekazywaniem do niej
 * danych. Przyciski sa ogolnoklasowe.
 * Do analizy.
 */
package launcher;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyGUI extends JFrame implements ActionListener {
	// tak wiec nasza klasa jest sama sobie frejmem
	// i sama sobie listenerem
	private static final long serialVersionUID = 4321;
	private static String title = "Æwiczenia";
	private short numOfEx = 0; // liczba cwiczen
	private static final short maxOfEx = 50; // max cwiczen
	private short whichEx = 0; // ktore cwiczenie bedzie wybrane
	private int width = 0 ;
	
	JButton butRun = new JButton("Wykonaj");
	JButton butEnd = new JButton("Koniec");
	JRadioButton[] exRadios = new JRadioButton[maxOfEx];

	// konstruktor
	public MyGUI() {
		super(title);

		MyDialog dial = new MyDialog();
		System.out.println("Praca domowa, lista cwiczen:");

		// policzmy ile mamy cwiczen
		for (Exercisable ex : Launcher.exercises) {
			System.out.println(ex.getTitle());
			// which is the longest one?
			if (width < ex.getTitle().length()){
				width = numOfEx ;
			}
			numOfEx++;
		}
		if (numOfEx > maxOfEx) {
			dial.display(MyDialog.MsgTypes.ERR, title, "Przekroczona maksymalna liczba cwiczen!");
			return;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// grupa radio
		ButtonGroup bGroup = new ButtonGroup();
		for (short i = 0; i < numOfEx; i++) {
			exRadios[i] = new JRadioButton(Launcher.exercises[i].getTitle());
			exRadios[i].setToolTipText(Launcher.exercises[i].getDescription());
			exRadios[i].addActionListener(this);
			bGroup.add(exRadios[i]);
			panel.add(exRadios[i]);
		}
		exRadios[0].setSelected(true);
		
		
		Font font = exRadios[0].getFont();
		
		int longestTxtWidth = MyPipedConsole.stringWidth(Launcher.exercises[width].getTitle(), font);
		int lineHeight = MyPipedConsole.stringHeight(Launcher.exercises[width].getTitle(), font);

		JPanel butPanel = new JPanel();
		
		butRun.addActionListener(this);
		butEnd.addActionListener(this);
		butPanel.add(butRun);
		butPanel.add(butEnd);
		butRun.setSelected(true);

		panel.add(butPanel);
		setBounds(200, 100, (int)(longestTxtWidth), lineHeight * 2 * numOfEx + 70);
		pack() ;
		setVisible(true);

	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		// System.out.println(source); // kontrola kontrolek
		// sprawdzamy ktory radiobutt jest zaznaczony:
		for (short i = 0; i < numOfEx; i++) {
			if (exRadios[i] == source) {
				whichEx = i;
			}
		}
		if (source == butEnd)
			System.exit(0); // alez jestesmy ordynarni
		if (source == butRun) {
			new SwingWorker<Void, String>() {
				protected Void doInBackground() throws Exception { 
					MyPipedConsole.getInstance () ;
					//new MyConsole () ;
					Launcher.exercises[whichEx].run();
					return null;
				} 
			}.execute(); 
		}
	}
}
