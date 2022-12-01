package matrix;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

/*
 * Tests to run in sequence.
 * To extend this, add a test class to the SuiteClasses, that is, the array.
 * @author Ron.Coleman
 */
@Suite.SuiteClasses({
        SliceStartTest.class,
        CommuteTest.class,
        Dice1Test.class,
        Dice2Test.class,
        Dice3Test.class,
        Dice4Test.class,
        Dice5Test.class,
        Dice6Test.class,
        Dice7Test.class,
        Slice2Test.class,
        Slice3Test.class,
        Slice4Test.class,
        Slice5Test.class,
        Slice6Test.class,
        Slice7Test.class,
        Slice8Test.class,
        Transpose1Test.class,
        Transpose2Test.class,
        Transpose3Test.class,
        Transpose4Test.class,
        Transpose5Test.class,
        Transpose6Test.class,
})

public class MopSuite {}
