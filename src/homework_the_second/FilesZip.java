package homework_the_second;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FilesZip implements Memorable {
	private String file = "liability.zip" ;
	// InputStream
	// OutputStream
	@Override
	public void save (Liability liability) throws Exception {
		ZipOutputStream zipOutputStream =  new ZipOutputStream (new FileOutputStream(file));
		
		ZipEntry zipe = new ZipEntry (file) ;
		zipOutputStream.putNextEntry(zipe);
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(zipOutputStream);
		objectOutputStream.writeObject(liability);
		objectOutputStream.close();	
		zipOutputStream.close();	
	}
	
	@Override
	public Liability load() throws Exception {
		ZipInputStream fis = new ZipInputStream (new FileInputStream(file));
		//ZipEntry zipe ; //= new ZipEntry (file) ;
		//zipe = 
		fis.getNextEntry();
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
