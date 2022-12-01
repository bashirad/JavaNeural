package mnist;

import neural.labs.lab7.MLoader;
import neural.mnist.MDigit;
import org.junit.Test;


public class Test1 {
        MLoader m = new MLoader("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte");
        MDigit digits = m.load()[28];

    @Test
    public void test() {
        System.out.println(digits.toString());
        assert (true);
    }
}


