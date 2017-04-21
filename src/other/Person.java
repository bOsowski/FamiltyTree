package other;

public class Person {

	private String name;
	private char gender;
	private String dateOfBirth;
	private Person mother;
	private Person father;

	
	public Person(String name, char gender, String dateOfBirth, Person mother, Person father) {
		super();
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mother = mother;
		this.father = father;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) {
		this.mother = mother;
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}


	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", mother=" + mother
				+ ", father=" + father + "]";
	}



}
