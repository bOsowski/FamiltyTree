//used https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html as a GUI tutorial.
package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import utilities.Loader;

/**
 * 
 * @author Bartosz Osowski, 20072283
 * @date 22/04/2017
 */

@SuppressWarnings("serial")
public class Driver extends JFrame implements WindowListener{
	
	private Loader loader;
	private ButtonListener btnListener;
	private JButton viewPersonBtn;
	private JButton addPersonBtn;
	private JTextField viewPersonText;
    
	private JTextField personsNameText;
	private JTextField personsDateOfBirthText;
	private Choice personsGenderChoice;
	private JTextField personsMotherText;
	private JTextField personsFatherText;
	private TextArea taDisplay;

	public static void main(String[] args) {
		new Driver();
	}
	
	public Driver() {
		loader = new Loader();
		mainMenu();
	}
	
	

	
	private void mainMenu(){
		//General Window settings
		setLayout(new FlowLayout());
	    setTitle("Family Tree");
	    setSize(400, 700); 
	    
	    //buttons
	    btnListener = new ButtonListener();
	    viewPersonBtn = new JButton("View person");
	    addPersonBtn = new JButton("Add person");
	    addPersonBtn.setPreferredSize(new Dimension(350,25));
	    viewPersonBtn.setPreferredSize(new Dimension(350, 25));
	    
	    //text fields
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

	    //adding to view
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
	    
	    //action listeners
	    viewPersonBtn.addActionListener(btnListener);
	    addPersonBtn.addActionListener(btnListener);   
	    
	    //Making the frame visible
	    setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);	
	}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
	
	private class ButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton)event.getSource();

			//on viewPersonBtn press..
			if(source == viewPersonBtn){
				String personName = viewPersonText.getText();
				taDisplay.setText(Loader.people.get(personName) + "\n\n\n"+personName+"'s Descendants: "+Loader.people.get(personName).toStringChildrenWithoutPerson()+"\n\n\n" + personName+"'s Ancestors: "+Loader.people.get(personName).toStringParentsWithoutPerson());
			}
			
			//on addPersonBtn press..
			else if(source == addPersonBtn){
				taDisplay.setText("Adding a person..");
				if(personsNameText.getText().length() >= 2 && !Loader.people.containsKey(personsNameText.getText())){
					loader.addPerson(personsNameText.getText(), personsGenderChoice.getSelectedItem(), personsDateOfBirthText.getText(), personsMotherText.getText(), personsFatherText.getText());
					taDisplay.setText("Person added.");
					
					//update the file
					try {
						loader.updateFile(personsNameText.getText(), personsGenderChoice.getSelectedItem().charAt(0)+"", personsDateOfBirthText.getText(), personsMotherText.getText(), personsFatherText.getText());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					//clear the fields
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
