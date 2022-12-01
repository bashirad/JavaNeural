package neural.mnist;

/**
 * Container of MNIST digits
 * @param no Digit number in the database
 * @param pixels 8-bit pixels
 * @param label Corresponding label
 * @author Bashir.Dahir
 */
public record MDigit(int no, double[] pixels, int label) {

    public static void main(String[] args) {
    /*DataInputStream dis =
            new DataInputStream(
                    new BufferedInputStream());*/
    /**
     *     to read the next integer:
     */
    //dis.readInt();
    /**
     *     to read the next ubyte:
     */
    //dis.readUnsignedByte();
    }

}
