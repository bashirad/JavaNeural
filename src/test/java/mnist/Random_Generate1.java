package mnist;

import neural.labs.lab7.MLoader;
import neural.mnist.MDigit;
import org.junit.Test;

import java.util.Random;

public class Random_Generate1 {

    MLoader m = new MLoader("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte");
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
