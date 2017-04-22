package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.Person;

public class Loader {
	
	public static HashMap<String, Person> people = new HashMap<String,Person>();

	public Loader() {
		try {
			loadInPeope(new File(getFile("Text Files", "txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fillPeoplesValues();
		
	}
	
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
	}
	
	public void fillPeoplesValues(){
		for(Map.Entry<String, Person> person: people.entrySet()){
			//fill parents for each person if they exist.
			//fill children for the parents.
			if(person.getValue().getMotherName() != null && people.containsKey(person.getValue().getMotherName())){
				//fill parents
				person.getValue().setMother(people.get(person.getValue().getMotherName()));
				//fill children
				people.get(person.getValue().getMotherName()).addChild(person.getValue());
			}
			if(person.getValue().getFatherName() != null && people.containsKey(person.getValue().getFatherName())){
				//fill parents
				person.getValue().setFather(people.get(person.getValue().getFatherName()));
				//fill children
				people.get(person.getValue().getFatherName()).addChild(person.getValue());
			}
		}
	}

}
