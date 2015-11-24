package demoVerifyElement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DemoFirefox.class, DemoIE.class, DemoChrome.class})
public class AllTests {

}
