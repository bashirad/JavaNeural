package mnist;
import neural.mnist.MDigit;
import org.junit.Test;

public class Test8 {
    MLoader m = new MLoader("data/t10k-images.idx3-ubyte", "data/t10k-labels.idx1-ubyte");
    MDigit digits = m.load()[28];

    @Test
    public void test() {
        assert (m.getChecksum() == 3838467927L);
    }
}