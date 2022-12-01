package mnist;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

/**
 * Tests to run in sequence.
 * To extend this, add a test class to the SuiteClasses, that is, the array.
 * @author Ron.Coleman
 */
@Suite.SuiteClasses({
        Test1.class,
        Test2.class,
        Test3.class,
        Test4.class,
        Test5.class,
        Test6.class,
        Test7.class,
        Test8.class,
        Random_Generate1.class,
        Random_Generate2.class


})



public class mnist_suite { }

