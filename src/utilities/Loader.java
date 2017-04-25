package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
	//				     //familyName     //personName
	public static HashMap<String,ArrayList<String>> families = new HashMap<String, ArrayList<String>>();
	public File file;

	public Loader() {
		file = new File(getFile("Database text files", "txt"));
		try {
			loadInPeope(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fillChildren();
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
	 * Fills in the children
	 */
	public void fillChildren(){
		for(Map.Entry<String, Person> person: people.entrySet()){
			//fill children for the parents.
			if(!person.getValue().getMother().equals("?") && people.containsKey(person.getValue().getMother())){
				//fill children
				people.get(person.getValue().getMother()).addChild(person.getValue().getName());
			}
			if(!person.getValue().getFather().equals("?") && people.containsKey(person.getValue().getFather())){
				//fill children
				people.get(person.getValue().getFather()).addChild(person.getValue().getName());
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
		fillChildren();
	}

}
