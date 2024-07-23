import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradeBookTest {

	/**
	 * @throws java.lang.Exception
	 */
	GradeBook GradeBook1;	
	GradeBook GradeBook2;
	@Before
	public void setUp() throws Exception {
		//Create 2 gradebook objects of size 5
		//and using addScore to add scores.
		GradeBook1= new GradeBook(5);
		GradeBook2=new GradeBook(5);
		GradeBook1.addScore(28);
		GradeBook1.addScore(27);
		GradeBook1.addScore(24);
		GradeBook1.addScore(23);
		GradeBook1.addScore(17);
		GradeBook2.addScore(45);
		GradeBook2.addScore(55);
		GradeBook2.addScore(22);
		GradeBook2.addScore(32);
		GradeBook2.addScore(12);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		GradeBook1=null;
		GradeBook2=null;
	}

	/**
	 * Test method for {@link GradeBook#addScore(double)}.
	 */
	@Test
	public void testAddScore() {
		assertTrue(GradeBook1.toString().equals("28.0 27.0 24.0 23.0 17.0 "));
		assertTrue(GradeBook2.toString().equals("45.0 55.0 22.0 32.0 12.0 "));
	}

	/**
	 * Test method for {@link GradeBook#sum()}.
	 */
	@Test
	public void testSum() {
		assertEquals(119, GradeBook1.sum(), .0001);
		assertEquals(166, GradeBook2.sum(), .0001);
		}

	/**
	 * Test method for {@link GradeBook#minimum()}.
	 */
	@Test
	public void testMinimum() {
		assertEquals(17, GradeBook1.minimum(), .0001);
		assertEquals(12, GradeBook2.minimum(), .0001);
		}

	/**
	 * Test method for {@link GradeBook#finalScore()}.	 */
	@Test
	public void testFinalScore() {
		assertEquals(102, GradeBook1.finalScore(), .0001);
		assertEquals(154, GradeBook2.finalScore(), .0001);
		}

	/**
	 * Test method for {@link GradeBook#getScoreSize()}.
	 */
	@Test
	public void testGetScoreSize() { 
		assertEquals(5, GradeBook1.getScoreSize(), .0001);
		assertEquals(5, GradeBook2.getScoreSize(), .0001);
		}

}