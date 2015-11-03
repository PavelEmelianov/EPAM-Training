package ua.nure.emelianov.Practice4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.emelianov.Practice4.part1.Part1Test;
import ua.nure.emelianov.Practice4.part2.Part2Test;
import ua.nure.emelianov.Practice4.part3.Part3Test;
import ua.nure.emelianov.Practice4.part4.ParserTest;
import ua.nure.emelianov.Practice4.part4.Part4Test;
import ua.nure.emelianov.Practice4.part5.Part5Test;

@RunWith(Suite.class)
@SuiteClasses({ Part1Test.class, Part2Test.class, Part3Test.class,
		Part4Test.class, Part5Test.class, ParserTest.class, DemoTest.class })
public class AllTests {

}
