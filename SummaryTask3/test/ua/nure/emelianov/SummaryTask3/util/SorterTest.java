package ua.nure.emelianov.SummaryTask3.util;

import org.junit.Before;
import org.junit.Test;

import ua.nure.emelianov.SummatyTask3.entity.Device;

public class SorterTest {
	
	public class DeviceMock extends Device{
		
	}
	
	Sorter sorter;

	@Before
	public void defaultConstructorTest() {
		sorter = new Sorter();
	}
	@Test
	public void sortByCriticaltest(){
		Sorter.sortPartsByCritical(new DeviceMock());
	}
	@Test
	public void sortByOrigintest(){
		Sorter.sortPartsByOrigin(new DeviceMock());
	}
	@Test
	public void sortByPricetesttest(){
		Sorter.sortPartsByPrice(new DeviceMock());
	}
}
