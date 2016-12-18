/*
package launcher;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main extends JFrame {
	private JButton start = new JButton("Start");
	private JButton quit = new JButton("Quit");
	private JButton clear = new JButton("Clear");
	private JTextArea text = new JTextArea();
	private JTextArea txtArea = new JTextArea();
	private PrintStream out = new PrintStream(new JTextComponentOutputStream(txtArea), true); // true
																				// oznacza
																								// autoflush

	static final private String title = "Main window";
	private int width = 500;
	private int height = 500;
	private int posX = 100;
	private int posY = 100;
	private JPanel panel = new JPanel();

	public Main() {
		super(title);

		System.setOut(out);
		Container cont = getContentPane();
		cont.setLayout(new BorderLayout());
		cont.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout());
		panel.add(start);
		quit.addActionListener(new QuitL());
		panel.add(quit);
		clear.addActionListener(new ClearL());
		panel.add(clear);
		text.getDocument().addDocumentListener(new TextL());
		text.setPreferredSize(new Dimension(50, 20));
		panel.add(text);
		txtArea.setEditable(false);
		cont.add(txtArea, BorderLayout.CENTER);
		setBounds(posX, posY, width, height);
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	class QuitL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Bye");
			System.exit(0);
		}
	}

	class ClearL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("");
			txtArea.setText("");
		}
	}

	class TextL implements DocumentListener {
		public void removeUpdate(DocumentEvent e) {
		}
		public void insertUpdate(DocumentEvent e) {
			char znak = text.getText().charAt(text.getText().length() - 1);
			System.out.print(znak);
			// out.print(znak);
		}
		public void changedUpdate(DocumentEvent e) {
		}
	}

	public void setwidth(int width) {
		this.width = width;
	}

	class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			System.out.println("mouse adapter");
		}
	}

	class MML extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
*/
// *****************************************************************************
// *****************************************************************************
// *****************************************************************************
