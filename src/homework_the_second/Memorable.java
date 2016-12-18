package homework_the_second;


public interface Memorable {
	String file = "" ;
	void save(Liability liability) throws Exception;
	Liability load() throws Exception;
	String getFile() ;
	void setFile(String file) ;

}
