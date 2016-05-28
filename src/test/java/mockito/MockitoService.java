package mockito;

public class MockitoService {
	private Dao dao;
	public String service(){
		return "realValue";
	}
	
	public String serviceWithArgs(String... strings){
		for(String arg:strings){
			System.out.println(arg);
		}
		return "realValue";
	}
	public void doService(String name){
		System.out.println("doService without returnValue");
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
}
class Dao {
	public void dao(){
		
	}
}
