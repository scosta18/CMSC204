package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerSTUDENT_Test {
	ArrayList<String> password1;
	String password2;

	@Before
	public void setUp() throws Exception {
		String[] r = {"556611NN", "mK12#gH", "lakers", "5sal#", "htyu33@"};
		password1 = new ArrayList<String>();
		password1.addAll(Arrays.asList(r));
	}

	@After
	public void tearDown() throws Exception {
		password1 = null;
	
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("fibo7"));
			PasswordCheckerUtility.isValidPassword("d47678");
			assertTrue("Did not throw lengthException", false);
		}
		catch(LengthException e) {
			assertTrue("Successfully threw a lengthException",true);
		}
		catch(Exception e) {
			assertTrue("Threw some other exception besides lengthException", false);
			
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {

			assertTrue(PasswordCheckerUtility.isValidPassword("4712587aB#"));
			PasswordCheckerUtility.isValidPassword("1234567a#");
			assertTrue("Did not throw NoUpperAlphaException", false);
		}
		catch(NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaException",true);
		}
		catch(Exception e) {
			assertTrue("Threw some toher exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("2389416Fv#"));
			PasswordCheckerUtility.isValidPassword("3467119B#");
			assertTrue("Did not throw NoLowerAlphaException", false);
		}
		catch(NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaException", true);
		}
		catch(Exception e) {
			assertTrue("Threw some other exception esides NoLowerAlphaException", false);
		}
		
		
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("7834nnCC@"));
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("5612nnD#");
			assertTrue(weakPwd);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception", false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("4567fVV@"));
		    PasswordCheckerUtility.isValidPassword("4567eCC&");
		    assertTrue("Did not throw an InvalidSquenceException", true);
		}
		catch(InvalidSequenceException e) {
			assertTrue("Successfully threw an InvalidQequenceException", true);
		}
		catch(Exception e) {
		System.out.println(e.getMessage());
		assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	
		}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("4567"));
			PasswordCheckerUtility.isValidPassword("456678");
			assertTrue("Did not throw an NoDigitException", true);
		}
		catch(NoDigitExeption e){
			assertTrue("Successfully throw an InvalidSequenceException", false);			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException", true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("4567"));
			PasswordCheckerUtility.isValidPassword("456@Ausio8");
			assertTrue("Did not throw an exception", true);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception", true);	
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String>invalidPasswordReslts;
		invalidPasswordReslts = PasswordCheckerUtility.getInvalidPasswords(password1);
		Scanner scan = new Scanner(invalidPasswordReslts.get(0));
		assertEquals(scan.next(), "556611NN");
	}
	
}