package homework_the_first;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import launcher.Exercisable;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.FlowLayout;

public class Exercise_5_2 extends JFrame implements Exercisable, Runnable {
	static final long serialVersionUID = 3;
	private Exercise_5_2 frame;
	private Triangle t = new Triangle();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblToNieTrjkt;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	public String getTitle() {
		return "5.2 Trójk¹t z edytora okien";
	}

	public String getDescription() {
		return "Test pracy z wtyczk¹ Eclipse'a.";
	}

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			frame = new Exercise_5_2();

			// frame.exe () ;
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Exercise_5_2() {
		// public void exe() {
		setTitle("Tr\u00F3jk\u0105t");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 363, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblBokiTrjkta = new JLabel("Boki tr\u00F3jk\u0105ta:");
		lblBokiTrjkta.setHorizontalAlignment(SwingConstants.LEFT);
		lblBokiTrjkta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblBokiTrjkta);

		JLabel lblA = new JLabel("a:");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblA);
		lblA.setLabelFor(textField);

		textField = new JTextField();
		textField.setToolTipText("Podaj d\u0142ugo\u015B\u0107 boku a");
		contentPane.add(textField);
		textField.setColumns(3);

		JLabel lblB = new JLabel("b:");
		lblB.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblB);
		lblB.setLabelFor(textField_1);

		textField_1 = new JTextField();
		textField_1.setToolTipText("Podaj d\u0142ugo\u015B\u0107 boku b");
		contentPane.add(textField_1);
		textField_1.setColumns(3);

		JLabel lblC = new JLabel("c:");
		lblC.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblC);

		textField_2 = new JTextField();
		textField_2.setToolTipText("Podaj d\u0142ugo\u015B\u0107 boku c");
		contentPane.add(textField_2);
		textField_2.setColumns(3);

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JLabel lblPole = new JLabel("Pole:");
		panel.add(lblPole);
		lblPole.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblToNieTrjkt = new JLabel("To nie tr\u00F3jk\u0105t");
		panel.add(lblToNieTrjkt);

		JButton btnNewButton = new JButton("Wylicz");
		panel.add(btnNewButton);
		btnNewButton.setAction(action_1);

		JButton btnKoniec = new JButton("Koniec");
		panel.add(btnKoniec);
		btnKoniec.setAction(action);
	}

	private class SwingAction extends AbstractAction {
		static final long serialVersionUID = 1;

		public SwingAction() {
			putValue(NAME, "Koniec");
			putValue(SHORT_DESCRIPTION, "Zakoñcz zabawê");
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class SwingAction_1 extends AbstractAction {
		static final long serialVersionUID = 2;

		public SwingAction_1() {
			putValue(NAME, "Wylicz");
			putValue(SHORT_DESCRIPTION, "Wylicza pole trójk¹ta o podanych bokach");
		}

		public void actionPerformed(ActionEvent e) {
			double a, b, c;
			try {
				a = Double.parseDouble(textField.getText());
				b = Double.parseDouble(textField_1.getText());
				c = Double.parseDouble(textField_2.getText());
			} catch (NumberFormatException f) {
				lblToNieTrjkt.setText("Podejrzane dane...");
				repaint();
				return;
			}

			if (!t.setSides(a, b, c)) {
				lblToNieTrjkt.setText("To nie trójk¹t");
			} else {
				lblToNieTrjkt.setText(Double.toString(Math.round(t.getArea() * 100) / 100));
			}
			repaint();
		}
	}
}
