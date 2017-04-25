package application;

import java.util.ArrayList;

import utilities.Loader;

public class Person {

	private String name;
	private char gender;
	private int dateOfBirth;
	private ArrayList<String> children = new ArrayList<String>();
	private String mother;
	private String father;

	
	public Person(String name, String gender, String dateOfBirth, String motherName, String fatherName){
		this.name = name;
		
		if(gender.charAt(0) == 'm' || gender.charAt(0) == 'M'){
			this.gender = 'M';
		}
		else if(gender.charAt(0) == 'f' || gender.charAt(0) == 'F'){
			this.gender = 'F';
		}
		
		if(dateOfBirth.matches("[0-9]+")){
			this.dateOfBirth = Integer.parseInt(dateOfBirth);
		}
		
		this.mother = motherName;
		this.father = fatherName;
		
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
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}

	public ArrayList<String> getChildren() {
		return children;
	}
	
	public void addChild(String childName){
		this.children.add(childName);
	}
	
	
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", mother=" + mother
				+ ", father=" + father + "]";
	}


	/**
	 * recursive function which adds a whole family with the specified name
	 * (which consists of people connected in any way) into Loader.families.
	 * @param familyName
	 * @param isFirstMethodCalled
	 */
	public void createFamily(String familyName ,boolean isFirstMethodCalled){
		//If the function is the first one called.. (every other function has to be called with this set to false);
		//Add the new family to the 
		if(isFirstMethodCalled){
			Loader.families.put(familyName, new ArrayList<String>());
		}
		
		//If the person hasn't been dealt with already..
		if(!Loader.families.get(familyName).contains(name)){
			Loader.families.get(familyName).add(name);
			
			if(!Loader.families.get(familyName).contains(mother)){
				if(!mother.equals("?")){Loader.people.get(mother).createFamily(familyName, false);}
			}
			if(!Loader.families.get(familyName).contains(father)){
				if(!father.equals("?")){Loader.people.get(father).createFamily(familyName, false);}
			}
			
			for(String child: children){
				if(!Loader.families.get(familyName).contains(child)){
					Loader.people.get(child).createFamily(familyName, false);
				}
			}
			
			
		}
	}


	public String toStringChildren(boolean isFirstMethodCalled) {
		String toString = "";
		if(!isFirstMethodCalled){toString += name + ", ";}

		if(!children.isEmpty()){
			for(String child: children){
				toString+=Loader.people.get(child).toStringChildren(false);
			}
		}
		return toString;
	}


	public String toStringAncestors(boolean isFirstMethodCalled) {
		String toString = "";
		if(!isFirstMethodCalled){toString += name + ", ";}
		
		if(!mother.equals("?")){ toString += Loader.people.get(mother).toStringAncestors(false);}
		if(!father.equals("?")){ toString += Loader.people.get(father).toStringAncestors(false);}
		
		return toString;
	}
	

}
