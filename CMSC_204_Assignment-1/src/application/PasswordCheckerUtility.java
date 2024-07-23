package application;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This designed application will check for valid passwords and certain rules must be followed to ensure the creation
 * of a valid password through user input. 
 * @author Herman Mann 
 */
public final class PasswordCheckerUtility extends java.lang.Object {
	
	

	/**
	 * The method will check for a special symbol and will use the "regular expression" construct. Checking will be
	 * done by going through the whole user-inputed password and not just an individual character.
	 * @param str - the password entered by user, to check to see if it contains special symbol/character
	 * @return True if user-inputed password contains a special character false otherwise. 
	 */
	public static boolean ifPasswordContainsSpecialSymbol(String str)
	{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(str);
		return (!matcher.matches());
	}
	/**
	 * A default no-arg PasswordCheckerUtility() constructor. 
	 */
	public PasswordCheckerUtility()
	{
		
	}
	/**
	 * The method is designed to check and see if a user-inputed password is valid or not. Returns true if valid password.
	 * Throws exceptions for invalid passwords. Valid if password is greater than or equal to 6 characters, has at least one 
	 * digit, has at least one upper-case alphabetic character, has at least one lower-case alphabetic character, and has no more
	 * than two of the same character in a row.
	 * @param passwordString - string to be checked for validity 
	 * @return - true if valid password, set up to return false if an invalid password, but throws an exception instead.
	 * @throws LengthException - thrown if length is less than 6 characters.
	 * @throws NoDigitException - thrown if no digit. 
	 * @throws NoUpperAlphaException - thrown if no uppercase alphabetic character in the user-inputed password. 
	 * @throws NoLowerAlphaException - thrown if no lowercase alphabetic character in the user-inputed password. 
	 * @throws InvalidSequenceException - thrown if more than 2 of the same character. 
	 * @throws NoSpecialCharacterException - thrown if a special character is in the user-inputed password.  
	 */
	public static boolean isValidPassword(String passwordString) throws LengthException,
	NoDigitExeption, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, 
	NoSpecialCharacterException
	{
	
		while(passwordString.length() < 6)
		{
			throw new LengthException(); //* throw a new length exception if password is less than 6 characters of size 
		}

		Pattern lower_Case = Pattern.compile("[a-z]");
		
		Pattern upper_Case = Pattern.compile("[A-Z]");
		
		Pattern specialCharacter = Pattern.compile("[a-zA-Z0-9]*");
		
		
		while(!(specialCharacter.matcher(passwordString).find()))
		{
			throw new NoSpecialCharacterException();
		}

		Pattern number = Pattern.compile("[0-9]");
		
		while(!(number.matcher(passwordString).find()))
		{
			throw new  NoDigitExeption();
		}
		
		int password_Length = passwordString.length();
		int firstCharacter = 1;
		int secondCharacter = 2;
		
		int j = 0; 
		
		while(j < password_Length)
		{
			if((j+firstCharacter < password_Length) && (j+secondCharacter < password_Length)) 
			{
				while((passwordString.charAt(j) == passwordString.charAt(j+firstCharacter)) && (passwordString.charAt(j+firstCharacter) ==
					passwordString.charAt(j+secondCharacter)))
				{
					throw new InvalidSequenceException();
						
				}
		
			}
			
			j++;
		}
			
		
		while(!(lower_Case.matcher(passwordString).find()))
		{
			throw new NoLowerAlphaException();
		}
		
		while(!(upper_Case.matcher(passwordString).find()))
		{
			throw new NoUpperAlphaException();
		}
		
		return true;	

	}
	
	/**
	 * Returns true if length of password is greater than or equal to 6 but less than or equal to 9. 
	 * @param passwordString - string to be checked if weak password. 
	 * @return - true if length of password is greater than or equal to 6 but less than or equal to 9. 
	 */
	public static boolean isWeakPassword(String passwordString)
	{
		int length_PasswordString = passwordString.length();
		
		boolean status = true;
		
		while (length_PasswordString >= 6 && length_PasswordString <= 9)
		{
			
			return status; /* will return true because this is the validation status of true of a weak password's length. */
			
		}
			 
			status = false;
			
			return status; /* returning the status, if the password entered by user is a weak password (6-9) characters or not. */
	}
	
	
	/**
	 * Returns an arraylist of invalid passwords (weak passwords are not considered invalid). 
	 * @param passwords - arraylist of passwords to check for validity.
	 * @return - arraylist of invalid passwords. It will not return weak passwords. 
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
	{
		Exception exceptionThrown = null; //* setting the exception thrown to be null at beginning. 
		
		ArrayList<String> invalidPasswords = new ArrayList<String>(); /* used for returning invalid passwords (user-inputed). */
		
		boolean the_Digit = false; //* setting this to be false and will be true if password contains 1 or more digit. 
		boolean ifUpperCase = false;//* setting this to be false and will be true if password contains 1 or more uppercase character. 
		boolean ifLowerCase = false; //* setting this to be false and will be true if password contains 1 or more lowercase character. 
		boolean ifTheSpecialSymbol = false; //* setting this to be false and will be true if password contains 1 or more special symbol.
		boolean if_ValidSequence = true; /* setting this to be true and will be false if password has more than 2 repeating characters
		in same sequence of repitition */
		
		for(String user_Password: passwords)
		{
			the_Digit = false; 
			ifLowerCase = false; 
			ifUpperCase = false; 
			ifTheSpecialSymbol = false;
			if_ValidSequence = true;
			
			Pattern passwordisUpperCase = Pattern.compile("[A-Z]"); //* used for compiling for finding uppercase in password. 
			Pattern passwordisLowerCase = Pattern.compile("[a-z]"); //* used for compiling for finding lowercase in password. 
			Pattern passwordisOfSpecialCharacter = Pattern.compile("[a-zA-Z0-9]*"); /* used for compiling for finding special character
			in password. */
			Pattern ifPasswordIsDigit = Pattern.compile("[0-9]"); /* used for compiling for finding a numerical digit in password */
			
			exceptionThrown = null; /* set the exception to be thrown to no value (null) since exception won't be thrown until exact
			type of error of exception is found within the password. */
			
			int lengthPassword = user_Password.length();
			
			if(lengthPassword < 6)
			{
				exceptionThrown = new LengthException(); /* exception will be thrown only if length of password is less than 6
				characters. */ 
			}
			
			int k = 0;
			
			while(k < lengthPassword)
			{
				
				if(ifPasswordIsDigit.matcher(user_Password).find())
				{
					the_Digit = true; //* will return true if 1 or more numerical digit character is found in password. 
				}
				
				if(passwordisLowerCase.matcher(user_Password).find())
				{
					ifLowerCase = true;
				}
				
				
				if(passwordisUpperCase.matcher(user_Password).find())
				{
					ifUpperCase = true;
				}
				
				k++;
			}
			
			if(ifPasswordContainsSpecialSymbol(user_Password))
			{
				ifTheSpecialSymbol = true; /* if password contains 1 or more special symbol, return true. */
			}
			
			if(the_Digit && ifLowerCase && ifUpperCase && ifTheSpecialSymbol && if_ValidSequence)
			{	
				int i = 0;
				
				int theNextElement = i+1;
				
				int nextElement_AfterThat = i+2;
				
				int passwordByUser = user_Password.length()-1;
				
				while(i < passwordByUser)
				{
					if((user_Password.charAt(i) == user_Password.charAt(theNextElement)) && (user_Password.charAt(i) == user_Password.charAt(nextElement_AfterThat)))
					{
						if_ValidSequence = false;
					}
					
					i++;
				}
				
			}
			
			if(!(the_Digit))
			{
				exceptionThrown = new NoDigitExeption();
			}
			else if(!(ifLowerCase))
			{
				exceptionThrown = new NoLowerAlphaException();
			}
			else if(!(ifUpperCase))
			{
				exceptionThrown = new NoUpperAlphaException();
			}
			else if(!(ifTheSpecialSymbol))
			{
				exceptionThrown = new NoSpecialCharacterException();
			}
			else if(!(if_ValidSequence))
			{
				exceptionThrown = new InvalidSequenceException();
			}
			
			String s_Character = " ";
			
			
			if(exceptionThrown != null)
			{
				invalidPasswords.add(user_Password + s_Character + exceptionThrown.getMessage());
			}
		}
		
		return invalidPasswords; /* return the passwords that are invalid. */
		

	}
	
}