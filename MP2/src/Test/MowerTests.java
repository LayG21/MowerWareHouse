import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MowerTests {
	
	@DisplayName("Test for default constructor")
	@Test
	void testMower1() {
		Mower mower = new LawnTractor();
		assertEquals("None",mower.getManufacturer());
		assertEquals(0, mower.getYear());
		assertEquals("None",mower.getSerialNumber());
	} 
	
	@DisplayName("Test for argument constructor")
	@Test
	void testMower2() {
		Mower mower = new LawnTractor();
		mower.setManufacturer("B&H Manufacturing");
		mower.setSerialNumber("AFXH4");
		mower.setYear(2003);
		
		assertEquals("B&H Manufacturing",mower.getManufacturer());
		assertEquals(2003, mower.getYear());
		assertEquals("AFXH4",mower.getSerialNumber());
	}
	@DisplayName("Test for setter and getters")
	@Test
	void testSettersandGetters() {
		Mower mower = new LawnTractor();
		
		mower.setManufacturer("B&H Manufacturing");
		mower.setSerialNumber("AFXG4");
		mower.setYear(2003);
		
		assertEquals("B&H Manufacturing",mower.getManufacturer());
		assertEquals("AFXG4",mower.getSerialNumber());
		assertEquals(2003,mower.getYear());
	}
	
	@DisplayName("Test for to String")
	@Test
	void testToString() {
		Mower mower = new LawnTractor();
		
		mower.setManufacturer("B&H Manufacturing");
		mower.setSerialNumber("AFXG4");
		mower.setYear(2003);
		
		String expected = "B&H Manufacturing\n2003\nAFXG4\nL\nNone\n0.0\n0\nNone\n0.0";
		assertEquals(expected,mower.toString());
	}

}
