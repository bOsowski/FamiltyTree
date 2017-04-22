package application;

import java.util.ArrayList;

public class Person {

	private String name;
	private char gender;
	private int dateOfBirth;
	private Person mother;
	private Person father;
	private ArrayList<Person> children = new ArrayList<Person>();
	private String motherName;
	private String fatherName;

	
	public Person(String name, char gender, int dateOfBirth, Person mother, Person father) {
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mother = mother;
		this.father = father;
	}
	
	public Person(String name, String gender, String dateOfBirth, String motherName, String fatherName){
		if(!name.equals("?")) this.name = name;
		else return;
		
		if(gender.charAt(0) == 'm' || gender.charAt(0) == 'M'){
			this.gender = 'M';
		}
		else if(gender.charAt(0) == 'f' || gender.charAt(0) == 'F'){
			this.gender = 'F';
		}
		
		if(dateOfBirth.matches("[0-9]+")){
			this.dateOfBirth = Integer.parseInt(dateOfBirth);
		}
		
		if(!motherName.equals("?")){
			this.motherName = motherName;
		}
		if(!fatherName.equals("?")){
			this.fatherName = fatherName;
		}
		
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
	public int getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(int dateOfBirth) {
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
	
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	

	public ArrayList<Person> getChildren() {
		return children;
	}
	
	public void addChild(Person child){
		this.children.add(child);
	}

	public void setChildren(ArrayList<Person> children) {
		this.children = children;
	}

	

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}

	public String toStringChildren() {
		String toReturn =  "(Person [name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", father= "+fatherName + ", mother= "+motherName;
		
		if(!children.isEmpty()){
			for(Person person: children){
				toReturn = toReturn  + ","+  "child=" + person.toStringChildren();
			}
		}
		
		return toReturn + "])";
	}
	
	public String toStringParents() {
		String toReturn =  "(Person [name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth;
		if(mother != null){
			 toReturn += "mother=" + mother.toStringParents();
		}
		else{
			toReturn += " mother = ?";
		}
		
		if(father != null){
			 toReturn  += " father=" + father.toStringParents();
		}
		else{
			toReturn += "father = ?";
		}
		
		return toReturn + "])";
	}
	
	public String toStringParentsWithoutPerson() {
		String toReturn =  "(";
		if(mother != null){
			 toReturn += " mother=" + mother.toStringParents();
		}
		else if(motherName != null && !motherName.equals("?")){
			toReturn += " mother= " + motherName;
		}
		else{
			toReturn += "mother = ?";
		}
		
		if(father != null){
			 toReturn += " father= " + father.toStringParents();
		}
		else if(fatherName != null && !fatherName.equals("?")){
			toReturn += " father= " + fatherName;
		}
		else{
			toReturn += " father = ?";
		}
		
		return toReturn + ")";
	}
	
	public String toStringChildrenWithoutPerson() {
		String toReturn =  "(";
		
		if(!children.isEmpty()){
			for(Person person: children){
				toReturn = toReturn  + ""+  "child=" + person.toStringChildren();
			}
		}
		
		return toReturn + ")";
	}



}
