package application;

public class UnmatchedException extends Exception {
	public UnmatchedException() {
		super("Entered password does not match.");
	}

}
