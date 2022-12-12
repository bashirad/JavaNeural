package neural.labs.lab7;

import neural.mnist.IMLoader;
import neural.mnist.MLetter;
import org.encog.mathutil.Equilateral;

import java.io.*;
import java.util.zip.CRC32;

public class MLoader implements IMLoader {
    CRC32 crc = new CRC32();
    private int magicNumber = 0;
    private int numItems = 0;
    private int rows = 0;
    private int columns = 0;

    MLetter currentpixel = null;

    MLetter[] loadData = null;

    private int added = 0;
    private int labelNumber = 0;
    private int numLabels = 0;
    private int labelOutput = 0;
    private double[] mDigitData;

    public final static double NORMALIZED_LO = 0;
    public final static double NORMALIZED_HI = 1;
    public final static int numDigits = 10;
    public static final Equilateral eq = new Equilateral(numDigits, NORMALIZED_HI, NORMALIZED_LO);


    String pixelpath = "C:\\Users\\maxje\\OneDrive\\Desktop\\Ai-Stuff\\MaxEnglishzJavaNeural\\data\\train-images.idx3-ubyte\\";
    String labelpath = "C:\\Users\\maxje\\OneDrive\\Desktop\\Ai-Stuff\\MaxEnglishzJavaNeural\\data\\train-labels.idx1-ubyte\\";

    public MLoader(String pixelpaths) {
        this.pixelpath = pixelpaths;
    }

    @Override
    //add try catch for file not found exception later
    public MLetter[] load() {
        loadData = null;
        try {
            DataInputStream d = new DataInputStream(new BufferedInputStream(new FileInputStream(pixelpath)));
            magicNumber = d.readInt();
            numItems = d.readInt();
            rows = d.readInt();
            columns = d.readInt();

            //DataInputStream labels = new DataInputStream(new BufferedInputStream(new FileInputStream(labelpath)));
            //this.labelNumber = labels.readInt();
            //this.numLabels = labels.readInt();
            loadData = new MLetter[numItems];

                for (int i = 0; i < numLabels; i++) {
                    mDigitData = new double[rows * columns];
                    //int labelOutput = labels.readUnsignedByte();
                    for (int j = 0; j < rows * columns; j++) {
                        int currentStream = d.readUnsignedByte();
                        crc.update(currentStream);
                        mDigitData[j] = currentStream;

                    }
                    MLetter currentpixel = new MLetter(mDigitData,1);
                    loadData[i] = currentpixel;
                }

        } catch (IOException e) {
            System.out.println(e);
        }
        return loadData;
    }


    @Override
    public int getPixelsMagic() {
        return magicNumber;
    }

    @Override
    public long getChecksum() {
        return crc.getValue();
    }

    public Normal normalize() {
        double[][] newPixel = new double[numItems][784];
        //double[][] newLabels = new double[numItems][9];
        for (int i = 0; i < loadData.length; i++) {
            MLetter n = loadData[i];
            for (int j = 0; j < n.pixels().length; j++) {
                newPixel[i][j] = (double) n.pixels()[j] / 255.0;
            }
           // newLabels[i] = eq.encode(n.label());
        }
        return new Normal(newPixel);
    }
}

