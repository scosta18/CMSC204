package application;

public class NoDigitExeption extends Exception {
	public NoDigitExeption() {
		super("The password must contain at least one digit. ");
		
	}

}
