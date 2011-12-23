package cviceni;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Ukol1Test {

	@Test(groups = {"want"}, expectedExceptions = IllegalArgumentException.class)
	public void testFaktorial1() {
		Assert.assertEquals(Ukol1.faktorial(0), 1);
		Assert.assertEquals(Ukol1.faktorial(1), 1);
		
		Assert.assertNotEquals(Ukol1.faktorial(1), 3);
		Assert.assertNotEquals(Ukol1.faktorial(2), 3);
		
		Assert.assertEquals(Ukol1.faktorial(2), Ukol1.faktorial(1) * 2);
		
		
		Ukol1.faktorial(-1);
	}
	
	@Test(groups = {"notWant"})
	public void testFaktorial2() {
		throw new UnsupportedOperationException("Zatim neimplementovano");
	}

	@Test(groups = {"notWant"})
	public void testFaktorial3() {
		throw new UnsupportedOperationException("Zatim neimplementovano");
	}
	
	
	@Test(groups = {"Want"})
	public void testConstructor() {
		Ukol1 ukol = new Ukol1();
	}
	
}
