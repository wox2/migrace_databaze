package cviceni;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Ukol2Test {

	@Test(groups = {"want"})
	public void test1Krat(){
		Assert.assertEquals(Ukol2.krat(0, 1), 0);
		Assert.assertEquals(Ukol2.krat(0, 1), Ukol2.krat(1, 0));
		Assert.assertEquals(Ukol2.krat(0, 5), Ukol2.krat(0, 1));
		Assert.assertEquals(Ukol2.krat(0, 0), Ukol2.krat(0, 1));
		
		Assert.assertEquals(Ukol2.krat(1, 5), 5);
		Assert.assertNotEquals(Ukol2.krat(1, 5), -5);

		Assert.assertEquals(Ukol2.krat(1, -5), -5);
		Assert.assertNotEquals(Ukol2.krat(1, -5), 5);
		
		
		Assert.assertEquals(Ukol2.krat(-1, 5), -5);
		Assert.assertEquals(Ukol2.krat(1, -5), -5);		
	}
	
	@Test(groups = {"want"})
	public void testAbs(){
		Assert.assertEquals(Ukol2.abs(0), 0);
		Assert.assertEquals(Ukol2.abs(1), 1);
		Assert.assertEquals(Ukol2.abs(-1), 1);		
	}
	
	@Test(groups = {"want"})
	public void testPlus(){
		Assert.assertEquals(Ukol2.plus(0, 1), 1);
		Assert.assertEquals(Ukol2.plus(1, 0), 1);
		Assert.assertEquals(Ukol2.plus(2, 1), Ukol2.plus(2, 1));
		Assert.assertEquals(Ukol2.plus(1, -1), 0);
		Assert.assertEquals(Ukol2.plus(1, -1), Ukol2.plus(5, -5));
		Assert.assertEquals(Ukol2.plus(-66, -1), -67);
		Assert.assertEquals(Ukol2.plus(1, 1), 2);
		Assert.assertEquals(Ukol2.plus(-1, -1), -2);
		Assert.assertEquals(Ukol2.plus(-1, 2), 1);
		Assert.assertEquals(Ukol2.plus(1, -2), -1);
	}
	
	
	@Test(groups = {"want"}, dataProvider = "numberProvider")
	public void testKrat(int nr1, int nr2){
		Assert.assertEquals(Ukol2.krat(nr1, nr2), nr1 * nr2);
	}
	
	@DataProvider(name ="numberProvider")
	public Object[][] provideNumbers(){
		return new Object[][]{
			{ 2 , 3 },
			{ 0 , 3},
			{-1 , -1},
		};
	
	}
	
	@Test(groups = {"want"})
	public void testConstructor(){
		Ukol2 ukol = new Ukol2();
	}
}