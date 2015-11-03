package ua.nure.emelianov.SummaryTask1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.emelianov.SummaryTask1.FillingOfTheGift.*;
import ua.nure.emelianov.SummaryTask1.GiftContainer.NewYearsGiftTest;

@RunWith(Suite.class)
@SuiteClasses({ DemoTest.class, AbstractSweetsTest.class, CandyTest.class,
		DonutTest.class, PieTest.class, NewYearsGiftTest.class })
public class AllTests {

}