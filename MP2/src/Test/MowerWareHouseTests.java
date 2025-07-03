import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MowerWareHouseTests {
	
	@DisplayName("Test for default constructor")
	@Test
	void testMowerWareHouse() {
		MowerWareHouse mowerHouse = new MowerWareHouse();
		assertEquals(null,mowerHouse.getStoreName());
		assertEquals(new ArrayList<Mower>(), mowerHouse.getMowersList());
		assertEquals("",mowerHouse.getOutString());
	} 


}
