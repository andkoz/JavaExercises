package launcher;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Caret;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;

public class MyPipedConsole extends JFrame implements KeyListener, ActionListener, DocumentListener, CaretListener {
	private int pos, caretPos;
	static final long serialVersionUID = 1;
	private JTextArea tAreaOut;
	private Caret caret;
	private String backTxt ;
	private JButton btnCloseConsole;
	private JButton btnCloseProgram;
	private JButton btnCharset;
	private JLabel lblCharset;

	private String[] wrapRadiosNames = { "Zawijaj s³owa", "Zawijaj litery", "Nie zawijaj" };
	private JRadioButton[] wrapRadios = new JRadioButton[wrapRadiosNames.length];
	private static MyPipedConsole instance = null;
	private Settings settings = null ;
	private String currentCharset, newCharset;
	byte[] oneByteArr = new byte[1];
	private int topLeftX = 450 ;
	private int topLeftY = 100 ;
	private int consoleFrameWidth, consoleFrameHeight ;
	private int lineWidth = 70;
	private int numOfLines = 20;
	private int fontSize = 14;
	private Map<String, Charset> charsets;

	private PipedInputStream inPipe = new PipedInputStream();
	private final PipedInputStream outPipe = new PipedInputStream();
	private PrintStream outPrintStream;
	PrintWriter inWriter;

	public MyPipedConsole() {
		this(false);
	}

	public MyPipedConsole(boolean checkForSysOut) {
		super();

		currentCharset = Charset.defaultCharset().displayName();
		
		System.setIn(inPipe);
		try {
			outPrintStream = new PrintStream(new PipedOutputStream(outPipe), true);
			System.setOut(outPrintStream);
			inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
		} catch (IOException e) {
			System.out.println("Error: " + e);
			return;
		}

		prepareConsole();

		if (checkForSysOut) {
			new SwingWorker<Void, String>() {
				@Override
				protected Void doInBackground() throws Exception {
					// check if someone will be trying to print to sysout
					// and give them back this first caught byte
					publish("" + (char) (outPipe.read()));
					activateConsole ();
					return null;
				}
				@Override
				protected void process(java.util.List<String> chunks) {
					for (String letter : chunks) {
						// we want to cram into
						tAreaOut.setText(letter + tAreaOut.getText());
					}
				}
				@Override
				protected void done() {
				}
			}.execute();
			
		} else {
			// turn on if we don't have to wait for printing to sysout
			activateConsole ();
		}

		// doing the System out
		new SwingWorker<Void, String>() {
			protected Void doInBackground() throws Exception {
				while (true) {
					// wait for character in sysout
					oneByteArr[0] = (byte) outPipe.read();
					// send it to GUI with correct encoding
					publish(new String(oneByteArr, currentCharset));
				}
			}

			@Override
			protected void process(java.util.List<String> chunks) {
				for (String letter : chunks) {
					tAreaOut.append(letter);
					pos = tAreaOut.getText().length();
					updateCaretLbl ();
				}
			}
		}.execute();

	}
	private void updateCaretLbl (){
		lblCharset.setText("Pos: " + pos + "caret: " + tAreaOut.getCaretPosition());
		
	}

	private boolean mySetCaret (int pos){
		try{
			caret.setDot(pos);
		}catch (Exception e){
			System.err.println("mySetCaret Exception");
			return false;
		}
		return true;
	}

	private boolean checkCaret (){
		caretPos = tAreaOut.getCaretPosition();
		if (caretPos <= pos) {
			return false ;
		}else{
			return true ;
		}
	}
	void mySleep (int milis){
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// ************ caret listener section
	public void caretUpdate(CaretEvent e) {
		caretPos = tAreaOut.getCaretPosition();
		if (caretPos < pos) {
	// illegal caret position - place it at first position of input
			caretPos = pos;
			mySetCaret(pos);
		}
		updateCaretLbl ();	
	}
// ************ document listener section
	public void removeUpdate(DocumentEvent e) {
		if (checkCaret()){
			// update input string
			backTxt = tAreaOut.getText().substring(pos);
		}
		updateCaretLbl ();
	}
	public void insertUpdate(DocumentEvent e) {
		if (checkCaret()){
			// update input string
			backTxt = tAreaOut.getText().substring(pos);
			if (backTxt.endsWith("\n")){
				// send data to System.in (without its ending '\n' - println will add it)
				inWriter.println(backTxt.substring(0, backTxt.length()-1)) ;
				// update pos - further modification of this part of text not allowed
				pos = tAreaOut.getText().length() ;
				// now our input string is empty
				backTxt = "" ;
			}
		}
	}

	public void changedUpdate(DocumentEvent e) {
	}
// ***********************************
// ********* keyboard listener section
	@Override
	public void keyPressed(KeyEvent e) {
		// disable ev. text selection
		caret.setDot(caretPos);
		
		int code = e.getKeyCode() ;
		switch (code){
			case KeyEvent.VK_BACK_SPACE:
				if (!checkCaret()){
					// attempt to illegal remove - disable it
					e.consume();
				}
			case KeyEvent.VK_ENTER:
				// don't break the input line - place the caret at the end of line
				if (caretPos < pos + backTxt.length()){
					mySetCaret (pos + backTxt.length());
				}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
// ************************************
// ***************** action listener section	
	public void actionPerformed(ActionEvent e) {
		if ((Object) btnCloseProgram == e.getSource()) {
			System.exit(0);

		} else if ((Object) btnCloseConsole == e.getSource()) {
			if (this == instance)
				instance = null;
			dispose();
		} else if ((Object) btnCharset == e.getSource()) {

			/*byte[] byteArr ;
			try {
				byteArr = tAreaOut.getText().getBytes(currentCharset);
				FileOutputStream file = new FileOutputStream ("kodowanie.txt"); 
				file.write(byteArr);
				file.close();
			} catch (Exception e1) {
				System.out.println("Exception");
				e1.printStackTrace();
			}*/
			if (settings == null) settings = new Settings () ;
			
			if (settings.isVisible()){
				settings.close();
			}else{
				settings.open();
			}
			
		} else {

		}

	}
// ********************************
	
	public static byte[] encode(byte[] arr, String fromCharsetName) {
		return encode(arr, Charset.forName(fromCharsetName), Charset.forName("UTF-8"));
	}

	public static byte[] encode(byte[] arr, String fromCharsetName, String targetCharsetName) {
		return encode(arr, Charset.forName(fromCharsetName), Charset.forName(targetCharsetName));
	}

	public static byte[] encode(byte[] arr, Charset sourceCharset, Charset targetCharset) {

		ByteBuffer inputBuffer = ByteBuffer.wrap(arr);

		CharBuffer data = sourceCharset.decode(inputBuffer);

		ByteBuffer outputBuffer = targetCharset.encode(data);
		byte[] outputData = outputBuffer.array();

		return outputData;
	}

	public void close() {
		dispose();
	}

	static protected MyPipedConsole getInstance() {

		if (instance == null) {
			instance = new MyPipedConsole(true);
		} else {
			// reassign sysin
			try {
				instance.inPipe.close();
				instance.inWriter.close();
				instance.inPipe = new PipedInputStream();
				instance.inWriter = new PrintWriter(new PipedOutputStream(instance.inPipe), true);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.setIn(instance.inPipe);
			
			instance.activateConsole ();
		}
		return instance;
	}

	private void prepareConsole() {
		pos = 0; // position that divides text between out and in
		setTitle("Podrêczna konsola");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, fontSize);
		int oneCharWidth = stringWidth("W", font);
		int lineHeight = stringHeight("W", font);
		consoleFrameWidth = lineWidth * oneCharWidth + 50;
		consoleFrameHeight = (int) (numOfLines * lineHeight * 1.3 + 100);
		setBounds(topLeftX, topLeftY, consoleFrameWidth, consoleFrameHeight);

		JPanel panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		tAreaOut = new JTextArea(numOfLines, lineWidth);
		tAreaOut.setFont(font);
		tAreaOut.setBackground(Color.BLACK);
		tAreaOut.setForeground(Color.LIGHT_GRAY);
		tAreaOut.setCaretColor(Color.WHITE);
		tAreaOut.setLineWrap(true);
		tAreaOut.setWrapStyleWord(true);
		//Document outDocument = tAreaOut.getDocument();
		tAreaOut.getDocument().addDocumentListener(this);
		tAreaOut.addCaretListener(this);
		tAreaOut.addKeyListener(this);
		caret = tAreaOut.getCaret();
		caret.setSelectionVisible(false);
		JScrollPane tAreaScroll = new JScrollPane(tAreaOut);

		panel.add(tAreaScroll);
		
		JPanel btnPanel = new JPanel();
		btnCloseConsole = new JButton("Zamknij konsolê");
		btnPanel.add(btnCloseConsole);
		btnCloseConsole.setToolTipText("Zamyka konsolê nie koñcz¹c programu");
		btnCloseConsole.addActionListener(this);
		
		btnCloseProgram = new JButton("Zamknij program");
		btnPanel.add(btnCloseProgram);
		btnCloseProgram.setToolTipText("Koñczy program");
		btnCloseProgram.addActionListener(this);

		lblCharset = new JLabel("Pos: " + pos);
		btnPanel.add(lblCharset);


		
		btnCharset = new JButton("Ustawienia");
		btnPanel.add(btnCharset);
		btnCharset.setToolTipText("Opcje funkcjonalne");
		btnCharset.addActionListener(this);
		 
		panel.add(btnPanel);
		
		pack();
		consoleFrameWidth = this.getWidth();
		consoleFrameHeight = this.getHeight();
		// settings window uses coordinates of main console window
		// so we construct it after console frame pack
		//settings = new Settings () ;

	}

	public void activateConsole (){
		// and then turn on console
		setVisible(true);
		toFront();
		// focus on text area
		tAreaOut.requestFocusInWindow();
		
	}
	public static int stringHeight(String str, Font font) {
		FontMetrics metrics = new FontMetrics(font) {
			private static final long serialVersionUID = 1L;
		};

		Rectangle2D bounds = metrics.getStringBounds(str, null);
		return (int) bounds.getHeight();

	}

	public static int stringWidth(String str, Font font) {
		FontMetrics metrics = new FontMetrics(font) {
			private static final long serialVersionUID = 1L;
		};

		Rectangle2D bounds = metrics.getStringBounds(str, null);
		return (int) bounds.getWidth();

	}

	private class Settings extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		public Settings () {
			super ();
			prepareSettings () ;
		}

		public void open () {
			setVisible (true) ;
		}
		public void close () {
			setVisible (false) ;
		}
		
		private void prepareSettings () {
			setTitle("Ustawienia");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			

			JPanel settingsPanel = new JPanel();
			setContentPane(settingsPanel);
			settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		
			JPanel paramPanel = new JPanel();

			// ********* charsets list section
			charsets = Charset.availableCharsets();
			String[] charsetNames = new String[charsets.size()];
			int i = 0;
			for (String str : charsets.keySet()) {
				charsetNames[i] = str;
				i++;
			}
			JList<String> charsetList = new JList<>(charsetNames);
			JScrollPane scrollPaneChar = new JScrollPane(charsetList);
			charsetList.setVisibleRowCount(4);
			paramPanel.add(scrollPaneChar);
			charsetList.setToolTipText("Wybór jednego z dostêpnych kodowañ");
			charsetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			charsetList.setSelectedValue(currentCharset, true);
			charsetList.ensureIndexIsVisible(charsetList.getSelectedIndex());
			charsetList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					newCharset = charsetList.getSelectedValue();
					byte[] byteArr;
					String txt = tAreaOut.getText();
					try {
						// 
						pos = 0 ; // preventing illegal caret positioning during text update
						byteArr = txt.getBytes(currentCharset);
						// transcoding text area
						tAreaOut.setText(new String(byteArr, newCharset));
						pos = tAreaOut.getCaretPosition() ;
						updateCaretLbl();
					} catch (UnsupportedEncodingException e1) {
						System.out.println("UnsupportedEncodingException");
						e1.printStackTrace();
					}
					currentCharset = newCharset;
				}
			});
			// **********************************
			
			// **************** wrap text radios section
			ButtonGroup bGroup = new ButtonGroup();
			JPanel radiosPanel = new JPanel();
			radiosPanel.setLayout(new BoxLayout(radiosPanel, BoxLayout.Y_AXIS));
			for (i = 0; i < wrapRadiosNames.length; i++) {
				wrapRadios[i] = new JRadioButton(wrapRadiosNames[i]);
				wrapRadios[i].addActionListener(this);
				bGroup.add(wrapRadios[i]);
				radiosPanel.add(wrapRadios[i]);
			}
			wrapRadios[0].setSelected(true);
			paramPanel.add(radiosPanel);
			// ***********************************
			settingsPanel.add(paramPanel);
			pack();
			setBounds(topLeftX + consoleFrameWidth - this.getWidth(), topLeftY + 30, this.getWidth(), this.getHeight());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (short i = 0; i < wrapRadiosNames.length; i++) {
				if ((Object) wrapRadios[i] == e.getSource()) {
					switch (i) {
					case 0:
						tAreaOut.setLineWrap(true);
						tAreaOut.setWrapStyleWord(true);
						break;
					case 1:
						tAreaOut.setLineWrap(true);
						tAreaOut.setWrapStyleWord(false);
						break;
					case 2:
						tAreaOut.setLineWrap(false);
						tAreaOut.setWrapStyleWord(true);
						break;
					}
				}
			}
			
		}

	}


}
