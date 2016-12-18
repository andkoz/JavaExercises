package homework_the_second;

import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FilesBinary implements Memorable {
	private String file = "liability.bin" ;
	// InputStream
	// OutputStream
	@Override
	public void save (Liability liability) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//liability.setIndexNaZapis(Loan.getIndex());
		objectOutputStream.writeObject(liability);
		objectOutputStream.close();	
	}
	
	@Override
	public Liability load() throws Exception {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Liability liability = (Liability) ois.readObject();	
		
		//Komputer.setIndex(sklep.getIndexNaZapis());
		ois.close();
		return liability;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
