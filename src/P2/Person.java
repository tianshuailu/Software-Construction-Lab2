package P2;

public class Person {

	private String name;
	private boolean tag;
	
	public Person(String n) {
		name = n;
	}
	public void tag(boolean tag) {
		this.tag = tag;
	}
	public boolean getTag() {
		return tag;
	}
	
}
