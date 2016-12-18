package launcher;

import java.io.InputStream;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class JTextComponentInputStream extends InputStream {
	private JTextArea textComponent;
	private boolean left;
	public JTextComponentInputStream() {
		super();
	}
	
	public JTextComponentInputStream(JTextComponent textComponent) {
		super();
		this.textComponent = (JTextArea) textComponent;
		left = false ;
	}
	
	@Override
	public int read() {
		int znak ;
		if (left == false){
		     try {
	             //System.out.print("|C|");   
		    	 //according to the docs read() should block until new input is available
	                synchronized (this) {
	                    this.wait();
	                }
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }				
		
			znak = textComponent.getText().charAt(textComponent.getText().length() - 1) ;
			if (znak == 10){
				znak = 13 ;
				left = true ;
			}
		}else{
			znak = 10 ;
			left = false ;
		}
		
		//System.out.print((char) znak);
		return znak ;
	}
}

