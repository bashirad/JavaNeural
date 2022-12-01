package neural.labs.lab7.mnist;

import neural.labs.lab7.MLoader;
import neural.mnist.MDigit;
import org.junit.Test;

public class Test5 {
    MLoader m = new MLoader("data/t10k-images.idx3-ubyte", "data/t10k-labels.idx1-ubyte");
    MDigit digits = m.load()[28];

    @Test
    public void test() {
        System.out.println(digits.toString());
        assert (true);
    }

}
