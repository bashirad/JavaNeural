package neural.labs.lab7.mnist;

/**
 * Container of MNIST digits
 * @param no Digit number in the database
 * @param pixels 8-bit pixels
 * @param label Corresponding label
 * @author Ron.Coleman
 */
public record MDigit(int no, double[] pixels, int label) {
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("--- Testing\n#" + no + " label " + label + "\n  0123456789012345678901234567\n");
        for (int i = 0; i < 28; i++) {
            str.append(i % 10 + " ");
            for (int j = 0; j < 28; j++) {

                if (this.pixels()[i * 28 + j] == 0.0) {
                    str.append(".");
                } else {
                    str.append(Integer.toHexString((int) this.pixels()[i * 28 + 7] / 16));
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}
