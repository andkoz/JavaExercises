package homework_the_second;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;


public class FilesJson implements Memorable {
	private String file = "liability.json" ;

	@Override
	public void save (Liability liability) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(file), liability);
	}
	
	@Override
	public Liability load() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		Liability liability = mapper.readValue(new File(file), Liability.class);
		
		return liability;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
