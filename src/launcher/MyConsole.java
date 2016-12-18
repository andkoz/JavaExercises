package launcher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import launcher.TerminalForm.InputStreamWorker;

import java.awt.FlowLayout;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextPane;
import javax.swing.SwingWorker;

import java.awt.TextArea;
import java.awt.TextComponent;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class MyConsole extends JFrame {
	boolean add13 ;
	boolean czytaj ;
	int znak ;
	static final long serialVersionUID = 1;
	private JPanel contentPane;
	JTextArea textArea;
	JTextArea textArea_1;
	PrintStream out;
	InputStream in;
	private JButton btnZamknij;
	private final Action action = new SwingAction();

	public MyConsole() {
		setTitle("Podrêczna konsola");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		textArea = new JTextArea(15, 35);
		panel.add(new JScrollPane(textArea));
		textArea_1 = new JTextArea(2, 35);
		panel.add(new JScrollPane(textArea_1));
		in = new JTextComponentInputStream(textArea_1); 
		textArea_1.getDocument().addDocumentListener(new TextL());

		out = new PrintStream(new JTextComponentOutputStream(textArea), true); 
																									
																			
		System.setOut(out);
		System.setIn(in);
	    //  InputStreamWorker instreamWorker = new InputStreamWorker(textArea, System.in);
	    //  instreamWorker.execute();
		System.out.println("System.out check... OK.");
		btnZamknij = new JButton("Zamknij");
		btnZamknij.setAction(action);
		panel.add(btnZamknij);
		setVisible(true);
		// pack () ;
		//Scanner sc = new Scanner(in);
		//System.out.println(sc.nextLine());
	}

	public PrintStream getOut() {
		return out;
	}
	public InputStream getIn() {
		return in;
	}
	public JTextComponent getInComponent() {
		return textArea_1;
	}

	/*
	   public class InputStreamWorker extends SwingWorker<Void, String> {
		      private Scanner scanner;
		      private JTextArea textArea;

		      private InputStreamWorker(JTextArea textArea, InputStream inStream) {
		         this.textArea = textArea;
		         scanner = new Scanner(inStream);
		      }

		      @Override
		      protected Void doInBackground() throws Exception {
		         while (scanner.hasNextLine()) {
		            publish(scanner.nextLine());
		         }
		         return null;
		      }

		      @Override
		      protected void process(List<String> chunks) {
		         for (String chunk : chunks) {
		            textArea.append(chunk + "\n");
		         }
		      }
		   }

	*/
	
	
	
	public class JTextComponentOutputStream extends OutputStream {
		private JTextArea textComponent;

		public JTextComponentOutputStream() {
			super();
		}

		public JTextComponentOutputStream(JTextComponent textComponent) {
			super();
			this.textComponent = (JTextArea) textComponent;
		}

		public void write(int b) throws IOException {
			try {
				textComponent.append(new String(new byte[] { (byte) b }));
			} catch (Exception e) {
				new MyDialog().display(null, "MyConsole", "Byk");
			}
		}
	}
	
	class TextL implements DocumentListener {
		public void removeUpdate(DocumentEvent e) {
			czytaj = true ;
		}
		public void insertUpdate(DocumentEvent e) {
			znak = textArea_1.getText().charAt(textArea_1.getText().length() - 1);
			//System.out.print((char)znak);
			czytaj = true ;
			// out.print(znak);
			synchronized (in) {
	            //maybe this should only notify() as multiple threads may
	            //be waiting for input and they would now race for input
	            in.notify();
	        }
		}
		public void changedUpdate(DocumentEvent e) {
			czytaj = true ;
		}
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SwingAction() {
			putValue(NAME, "Zamknij");
			putValue(SHORT_DESCRIPTION, "Zamyka okno konsoli");
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
