package aknakereso;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TablaTestFixture {

	CustomFrame ablak;
	
	@Before
	public void setUp()
	{
		ablak = new CustomFrame();
		ablak.getLowerPanel().ujJatek.doClick();
		ablak.getLowerPanel().konnyuB.doClick();
	}
	@Test
	public void tablaMeretTeszt() 
	{
		Assert.assertSame(8*8, ablak.getLowerPanel().aJatek.mezoList.size());
	}
	@Test
	public void elsoLepesTeszt() 
	{
		Random rand = new Random();
		int myIndex =  rand.nextInt(8*8);
		ablak.getLowerPanel().aJatek.mezoList.get(myIndex).doClick();
		Assert.assertSame(' ', ablak.getLowerPanel().aJatek.mezoList.get(myIndex).getContent());
	}

}
