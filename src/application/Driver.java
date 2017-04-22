package application;

import utilities.Loader;

public class Driver {
	
	public static void main(String[] args) {
		new Driver();

	}
	

	public Driver() {
		Loader loader = new Loader();
		String name = "Elvin";
		System.out.println(name+"'s Descendants: "+Loader.people.get(name).toStringChildren());
		System.out.println(name+"'s Ancestors: "+Loader.people.get(name).toStringParents());
		
	}


}
