import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GasPoweredTests {
	
	@DisplayName("Test for default constructor")
	@Test
	void testGasPoweredMower() {
		GasPoweredMower gp = new GasPoweredMower();
		assertEquals(null,gp.getEngine());
		assertEquals(false,gp.getSelfPropelled());
		
	}
	
	@DisplayName("Test for setters and getters")
	@Test
	void testSettersabdGetters() {
		GasPoweredMower gp = new GasPoweredMower();
		
		
		gp.setEngine(new Engine("B&H Manufacturing",10.5,4));
		
		gp.setSelfPropelled(true);
		
		assertEquals("B&H Manufacturing",gp.getEngine().getManufacturer());
		assertEquals(10.5,gp.getEngine().getHorsePower());
		assertEquals(4,gp.getEngine().getCylinders());
		assertEquals(true,gp.getSelfPropelled());
		
		
	}
	
	@DisplayName("Test for toString")
	@Test
	void testToString() {
		GasPoweredMower gp = new GasPoweredMower();
		
		gp.setManufacturer("Bush Hog Manufacturing");
		gp.setYear(2013);
		gp.setSerialNumber("AFXH3");
		
		gp.setCutWidth(20.0);
		gp.setWheelDiameter(16.0);
		
		gp.setEngine(new Engine("Homelite Parts",10.5,2));
		
		gp.setSelfPropelled(true);
		
		
		String expected = "Bush Hog Manufacturing\n2013\nAFXH3\nG\n20.0\n16.0\nHomelite Parts\n10.5\n2\ntrue";
		
		assertEquals(expected,gp.toString());
	}


}
