package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.Person;

public class Loader {
	
	public static HashMap<String, Person> people = new HashMap<String,Person>();
	public File file;

	public Loader() {
		file = new File(getFile("Database text files", "txt"));
		try {
			loadInPeope(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fillPeoplesValues();
	}
	
	/**
	 * Appends the last added person to the end of the database file.
	 * 
	 * @param name
	 * @param gender
	 * @param dob
	 * @param mother
	 * @param father
	 * @throws IOException
	 */
	public void updateFile(String name, String gender, String dob, String mother, String father) throws IOException{
		Writer output = new BufferedWriter(new FileWriter(file, true));
		
		//other values aren't possible to be null
		if(dob == null){
			dob = "?";
		}
		if(mother == null){
			mother = "?";
		}
		if(father == null){
			father = "?";
		}
		

		output.append("\n"+name+" "+gender+" "+dob+" "+mother+" "+father);
		output.close();
	}
	
	/**
	 * FileChooser
	 * @param fileType
	 * @param fileExtension
	 * @return filePath
	 */
	private String getFile(String fileType, String fileExtension){
		String filePath = "";
		JFrame jf = new JFrame();
        jf.setAlwaysOnTop( true );
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter(fileType,fileExtension);
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog( jf );  // changed
        filePath = fileChooser.getSelectedFile().getPath();
        jf.dispose(); // added
        
        return filePath;
	}
	
	/**
	 * loads and parses the file
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void loadInPeope(File file) throws FileNotFoundException{
		Scanner scanner = new Scanner(file);
		String line;	//will be holding each line from the file that scanner parses in
		
		while(scanner.hasNext()){
			line = scanner.nextLine();
			
			String[] lineTokens = line.split("\\s++");
			
			if(lineTokens.length != 5){
				System.out.println("Not 5!:   "+ line);
			}
			
			if(!people.containsKey(lineTokens[0])){
				people.put(lineTokens[0], new Person(lineTokens[0], lineTokens[1], lineTokens[2], lineTokens[3], lineTokens[4]));
			}
		}
		
		scanner.close();
	}
	
	/**
	 * Fills in the mother,father and children Person variables of people.
	 */
	public void fillPeoplesValues(){
		for(Map.Entry<String, Person> person: people.entrySet()){
			//fill parents for each person if they exist.
			//fill children for the parents.
			if(person.getValue().getMotherName() != null && people.containsKey(person.getValue().getMotherName()) && person.getValue().getMother() == null){
				//fill parents
				person.getValue().setMother(people.get(person.getValue().getMotherName()));
				//fill children
				people.get(person.getValue().getMotherName()).addChild(person.getValue());
			}
			if(person.getValue().getFatherName() != null && people.containsKey(person.getValue().getFatherName()) && person.getValue().getFather() == null){
				//fill parents
				person.getValue().setFather(people.get(person.getValue().getFatherName()));
				//fill children
				people.get(person.getValue().getFatherName()).addChild(person.getValue());
			}
		}
	}
	
	/**
	 * calls the normal constructor and also calls a function responsible for filling other values.
	 * @param name
	 * @param gender
	 * @param dateOfBirth
	 * @param motherName
	 * @param fatherName
	 */
	public void addPerson(String name, String gender, String dateOfBirth, String motherName, String fatherName){
		people.put(name, new Person(name,gender,dateOfBirth,motherName,fatherName));
		fillPeoplesValues();
	}

}
