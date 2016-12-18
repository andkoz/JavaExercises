package homework_the_second;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FilesXml implements Memorable {
	private String file = "liability.xml" ;

	@Override
	public void save(Liability liability) throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Liability.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      //WYŒWIETLENIE NA OUT
      //  jaxbMarshaller.marshal(liability, System.out);
		jaxbMarshaller.marshal(liability, new File(file));
		
    }

	@Override
	public Liability load() throws Exception {
		   File xmlFile = new File(file);
	        JAXBContext jaxbContext = JAXBContext.newInstance(Liability.class);
	 
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        Liability liability = (Liability) jaxbUnmarshaller.unmarshal(xmlFile);
	        
		return liability;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}


}
