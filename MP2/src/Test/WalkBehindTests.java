import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalkBehindTests {
	
	@DisplayName("Test for default constructor")
	@Test
	void testWalkBehindMower() {
		WalkBehindMower wb = new GasPoweredMower();
		assertEquals(0.0, wb.getCutWidth());
		assertEquals(0.0,wb.getWheelDiameter());
		
	}
	
	//Setters and Getters
	
	@DisplayName("Test for setters and getters")
	@Test
	void testSettersAndGetters() {
		WalkBehindMower wb = new GasPoweredMower();
		wb.setCutWidth(24.5);
		wb.setWheelDiameter(10.0);
		
		assertEquals(24.5,wb.getCutWidth());
		assertEquals(10.0,wb.getWheelDiameter());
	}
	
	@DisplayName("Test for toString")
	@Test
	void testToString() {
		WalkBehindMower wm = new PushReelMower();
		
		wm.setCutWidth(20.0);
		wm.setWheelDiameter(15.2);
		
		String expected = "None\n0\nNone\nP\n20.0\n15.2\n0";
		
		
		
		assertEquals(expected,wm.toString());
		
	}

}
