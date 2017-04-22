//used https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html as a GUI tutorial.
package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilities.Loader;

public class Driver extends JFrame implements WindowListener{
	Loader loader;
	//JPanel mainCard;
	//JPanel addPersonCard;
	ButtonListener btnListener;
    JButton viewPersonBtn;
    JButton addPersonBtn;
    JTextField viewPersonText;
    
    JTextField personsNameText;
    JTextField personsDateOfBirthText;
    Choice personsGenderChoice;
    JTextField personsMotherText;
    JTextField personsFatherText;
    TextArea taDisplay;

	public static void main(String[] args) {
		new Driver();
	}
	
	public Driver() {
		
		loader = new Loader();
		mainMenu();
	}
	
	

	
	private void mainMenu(){
		setLayout(new FlowLayout());	//sets 
	    setTitle("Family Tree");  // "super" Frame sets its title
	    
	    btnListener = new ButtonListener();
	    viewPersonBtn = new JButton("View person");
	    addPersonBtn = new JButton("Add person");
	    addPersonBtn.setPreferredSize(new Dimension(350,25));
	    viewPersonBtn.setPreferredSize(new Dimension(350, 25));
	    
	    viewPersonText = new JTextField("", 30);
	    personsNameText = new JTextField("", 30);
	    personsDateOfBirthText = new JTextField("", 30);
	    personsGenderChoice= new Choice();
	    personsGenderChoice.add("Male");
	    personsGenderChoice.add("Female");
	    personsGenderChoice.setPreferredSize(new Dimension(350, 25));
	    personsMotherText= new JTextField("", 30);
	    personsFatherText= new JTextField("", 30);
	    taDisplay = new TextArea();
	    taDisplay.setPreferredSize(new Dimension(350, 200));
	   // taDisplay.setLineWrap(true);
	   // taDisplay.setWrapStyleWord(true);


	    add(new Label("name"));
	    add(viewPersonText);	
		add(viewPersonBtn);

	    
	    add(new Label("name"));
	    add(personsNameText);
	    add(new Label("date of birth"));
	    add(personsDateOfBirthText);
	    add(new Label("gender"));
	    add(personsGenderChoice);
	    add(new Label("mother's name"));
	    add(personsMotherText);
	    add(new Label("father's name"));
	    add(personsFatherText);
	    add(addPersonBtn);
	    add(taDisplay);
	    
	    viewPersonBtn.addActionListener(btnListener);
	    addPersonBtn.addActionListener(btnListener);

	    setSize(400, 700);        // "super" Frame sets its initial window size
	    
	   // pack();
	    setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}
	@Override
	public void windowClosed(WindowEvent e) {
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);	
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
	}
	@Override
	public void windowIconified(WindowEvent e) {	
	}
	@Override
	public void windowOpened(WindowEvent e) {	
	}
	
	private class ButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton)event.getSource();

			if(source == viewPersonBtn){
				System.out.println("View a person");
				String personName = viewPersonText.getText();
				taDisplay.setText(personName+"'s Descendants: "+Loader.people.get(personName).toStringChildrenWithoutPerson()+"\n\n\n");
				taDisplay.setText(taDisplay.getText() + personName+"'s Ancestors: "+Loader.people.get(personName).toStringParentsWithoutPerson());
			}
			
			else if(source == addPersonBtn){
				taDisplay.setText("Adding a person..");
				if(personsNameText.getText().length() >= 2 && !Loader.people.containsKey(personsNameText.getText())){
					loader.addPerson(personsNameText.getText(), personsGenderChoice.getSelectedItem(), personsDateOfBirthText.getText(), personsMotherText.getText(), personsFatherText.getText());
					taDisplay.setText("Person added.");
					
					personsNameText.setText("");
					personsDateOfBirthText.setText("");
					personsMotherText.setText("");
					personsFatherText.setText("");
				}
				
				else if(Loader.people.containsKey(personsNameText.getText())){
					taDisplay.setText("This person already exists!");
				}
				
				else{
					taDisplay.setText("Unable to add a person.\nCheck the required fields.");
				}
				
			}
		
		}
	}

}
