import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LawnTractorTests {

	@DisplayName("Test case for LawnTractor constructor")
	@Test
	void testLawnTractor() {
		LawnTractor lt = new LawnTractor();
		
		assertEquals("None",lt.getModel());
		assertEquals(0.0,lt.getDeckWidth());
	}
	
	@DisplayName("Test case for setters and getters in LawnTractor")
	@Test
	void testSettersAndGetters() {
		Engine engine = new Engine("Gravely Parts",10.8,2);
		
		
		LawnTractor lt = new LawnTractor();
		
		lt.setEngine(engine); 
		lt.setModel("Model1");  
		lt.setDeckWidth(45.5);
		
		assertEquals("Gravely Parts",lt.getEngine().getManufacturer());
		assertEquals(10.8,lt.getEngine().getHorsePower());
		assertEquals(2,lt.getEngine().getCylinders());
		assertEquals("Model1",lt.getModel());
		assertEquals(45.5,lt.getDeckWidth());
	}
	
	@DisplayName("Test for toString in LawnTractor") 
	@Test
	void testToString() {
		LawnTractor lt = new LawnTractor();
		
		lt.setManufacturer("Briggs and Stratton");
		lt.setYear(2015);
		lt.setSerialNumber("AFXH1");
		
		lt.setEngine(new Engine("Gravely Parts",10.8,2));
		
		lt.getEngine().setManufacturer("Gravely Parts");
		lt.getEngine().setHorsePower(10.8);
		lt.getEngine().setCylinders(2);
		
		lt.setModel("Model1");
		lt.setDeckWidth(45.5);
		
		String expected = "Briggs and Stratton\n2015\nAFXH1\nL\nGravely Parts\n10.8\n2\nModel1\n45.5" ;
		assertEquals(expected,lt.toString());
	}

}


