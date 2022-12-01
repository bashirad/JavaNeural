package mnist;
import neural.mnist.MDigit;
import org.junit.Test;

public class Test3 {
    MLoader m = new MLoader("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte");
    MDigit digits = m.load()[28];

    @Test
    public void test() {
        assert (m.getLabelsMagic() == 2049);
    }
}
