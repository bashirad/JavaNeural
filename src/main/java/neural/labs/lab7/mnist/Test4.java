package neural.labs.lab7.mnist;

import neural.labs.lab7.MLoader;
import neural.mnist.MDigit;
import org.junit.Test;

public class Test4 {
    MLoader m = new MLoader("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte");
    MDigit digits = m.load()[28];

    @Test
    public void test() {
        assert (m.getChecksum() == 4083391318L);
    }
}
