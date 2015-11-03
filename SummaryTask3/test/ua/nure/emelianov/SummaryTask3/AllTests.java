package ua.nure.emelianov.SummaryTask3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.emelianov.SummaryTask3.constants.ConstantsTest;
import ua.nure.emelianov.SummaryTask3.constants.XMLTest;
import ua.nure.emelianov.SummaryTask3.controller.DOMControllerTest;
import ua.nure.emelianov.SummaryTask3.controller.SAXControllerTest;
import ua.nure.emelianov.SummaryTask3.controller.STAXControllerTest;
import ua.nure.emelianov.SummaryTask3.entity.DeviceTest;
import ua.nure.emelianov.SummaryTask3.entity.PartTest;
import ua.nure.emelianov.SummaryTask3.entity.PortTest;
import ua.nure.emelianov.SummaryTask3.entity.TypeTest;
import ua.nure.emelianov.SummaryTask3.util.SorterTest;

@RunWith(Suite.class)
@SuiteClasses({ DemoTest.class, MainTest.class,XMLValidatorTest.class,ConstantsTest.class, XMLTest.class,
	DOMControllerTest.class, SAXControllerTest.class,STAXControllerTest.class,
	SorterTest.class,DeviceTest.class,PartTest.class,PortTest.class,TypeTest.class})
public class AllTests {

}
