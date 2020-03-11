package Èí¼ş²âÊÔ;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TDD_test {
	@Test
	public void TestTdd(){
		TDD tdd = new TDD();
		String str = "Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S";
		assertEquals("Black win!",tdd.input(str));
	}
}
