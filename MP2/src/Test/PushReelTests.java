import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PushReelTests {
	
	@DisplayName("Test for default constructor")
	@Test
	void testPushReelMower() {
		PushReelMower pw = new PushReelMower();
		assertEquals(0, pw.getNumWheels());
	}
	
	@DisplayName("Tests for setters and getters")
	@Test
	void testSettersandGetters() {
		PushReelMower pw = new PushReelMower();
		
		pw.setNumWheels(4);
		
		assertEquals(4,pw.getNumWheels());
	}
	
	@DisplayName("Test for toString")
	@Test
	void testToString() {
		PushReelMower pr = new PushReelMower();
		
		pr.setManufacturer("Ferris Industries");
		pr.setYear(2003);
		pr.setSerialNumber("AFXH4");
		
		pr.setCutWidth(18.0);
		pr.setWheelDiameter(25.1);
		
		pr.setNumWheels(4);
		
		String expected = "Ferris Industries\n2003\nAFXH4\nP\n18.0\n25.1\n4";
		
		assertEquals(expected,pr.toString());
	}

}
