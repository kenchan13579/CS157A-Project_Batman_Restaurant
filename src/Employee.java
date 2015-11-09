
public class Employee extends Person{
	private String position;
	public Employee(String fn, String ln , String email , String position) {
		super(fn, ln, email);
		this.position = position;
		// TODO Auto-generated constructor stub
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
