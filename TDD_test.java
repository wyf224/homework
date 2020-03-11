import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TDD_test {
	@Test
	public void TestTdd(){
		TDD tdd = new TDD();
		String str1 = "Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S";
		String str2 = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";
		assertEquals("Black win!",tdd.input(str1));
		assertEquals("White win!",tdd.input(str2));
	}
}
