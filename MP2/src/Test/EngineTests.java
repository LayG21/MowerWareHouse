import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EngineTests {
	

	@DisplayName("Test for default Engine constructor")
	@Test
	void testEngine1() {
		Engine engine = new Engine();
		assertEquals("None", engine.getManufacturer());
		assertEquals(0.0,engine.getHorsePower());
		assertEquals(0,engine.getCylinders());
	}
	
	@DisplayName("Test for argument constructor")
	@Test
	void testEngine2() {
		Engine engine = new Engine("Gravely Parts",10.8,2);
		
		assertEquals("Gravely Parts",engine.getManufacturer());
		assertEquals(10.8,engine.getHorsePower());
		assertEquals(2,engine.getCylinders());
	}
	
	@DisplayName("Test for setters and getters")

	@Test
	void testSettersandGetters() {
		Engine engine = new Engine();
		
		engine.setManufacturer("Gravely Parts");
		engine.setHorsePower(10.8);
		engine.setCylinders(2);
		
		assertEquals("Gravely Parts",engine.getManufacturer());
		assertEquals(10.8,engine.getHorsePower());
		assertEquals(2,engine.getCylinders());
	}
	
	@DisplayName("Test for toString")
	@Test
	void testToString() {
		Engine engine = new Engine("Gravely Parts",10.8,2);
		
		String expected = "Gravely Parts\n10.8\n2";
		
		assertEquals(expected,engine.toString());
	}

}
