import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommercialMowerTests {
	
	@DisplayName("Test for constructor")
	@Test
	void CommercialMower() {
		CommercialMower cm = new CommercialMower();
		
		assertEquals(0.0,cm.getOperatingHours());
		assertEquals(false,cm.getZeroTurnRadius());
	}
	
	@DisplayName("Test for Setters and Getters")
	@Test
	void testSettersAndGetters() { 
		CommercialMower cm = new CommercialMower();
		
		cm.setOperatingHours(35.5);
		cm.setZeroTurnRadius(false);
		
		
		assertEquals(35.5,cm.getOperatingHours());
		assertEquals(false,cm.getZeroTurnRadius());
	}
	
	@DisplayName("Test for toString")
	@Test
	void testToString() {
		CommercialMower cm = new CommercialMower();
		
		cm.setManufacturer("B&H Manufacturing");
		cm.setYear(2011);
		cm.setSerialNumber("AFXH2");
		
		
		cm.setEngine(new Engine("Poulan Parts",10.5,2));
		
		
		cm.setModel("Model2");
		cm.setDeckWidth(5.8);
		
		cm.setOperatingHours(35.5);
		cm.setZeroTurnRadius(false);
		
		String expected = "B&H Manufacturing\n2011\nAFXH2\nC\nPoulan Parts\n10.5\n2\nModel2\n5.8\n35.5\nfalse";
		
		assertEquals(expected, cm.toString());
	}

}
