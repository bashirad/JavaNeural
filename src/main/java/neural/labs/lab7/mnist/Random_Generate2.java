package neural.labs.lab7.mnist;

import neural.labs.lab7.MLoader;
import neural.mnist.MDigit;
import org.junit.Test;

import java.util.Random;

public class Random_Generate2 {

    MLoader m = new MLoader("data/t10k-images.idx3-ubyte", "data/t10k-labels.idx1-ubyte");
    MDigit[] digits = m.load();

    Random random = new Random();


    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            int idx = random.nextInt(digits.length);
            System.out.println(digits[idx]);
        }
        assert (true);
    }
}
