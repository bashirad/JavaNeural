package neural.labs.lab7.mnist;


import neural.labs.lab7.MLoader;
import neural.mnist.IMLoader;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BashirzMTesting {

    /**
     * Inputs and ideals necessary for testing.
     */
    public static double[][] TESTING_INPUTS;
    public static double[][] TESTING_IDEALS;

    private static final DecimalFormat decimalFormat1 = new DecimalFormat("00.0");
    static SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");


    public static void init() {

        String pixelPath = "C:\\Users\\Bashir\\Documents\\Bashirs_Code_all\\Java\\BashirzJavaNeural\\data\\t10k-images.idx3-ubyte";
        String labelPath = "C:\\Users\\Bashir\\Documents\\Bashirs_Code_all\\Java\\BashirzJavaNeural\\data\\t10k-labels.idx1-ubyte";
        MLoader loader = new MLoader(pixelPath, labelPath);
        loader.load();


        IMLoader.Normal normal = loader.normalize();

        TESTING_INPUTS = normal.pixels();
        assert(TESTING_INPUTS[0].length == 784);

        TESTING_IDEALS = normal.labels();
        assert(TESTING_IDEALS[0].length == (10-1));


    }

    public static void main(final String[] args) {

        init();

        BasicNetwork network =
                (BasicNetwork) EncogDirectoryPersistence.loadObject(new
                        File("C:\\Users\\Bashir\\Documents\\Bashirs_Code_all\\Java\\BashirzJavaNeural\\data\\encogmnist-2000.bin"));
        MLDataSet testingSet = new BasicMLDataSet(TESTING_INPUTS, TESTING_IDEALS);

        MExercise ex = new MExercise(network, testingSet);
        int len = testingSet.size();
        int data = ex.report().data();

        String successRate = decimalFormat1.format((( float )data / ( float ) len ) * 100 );
        System.out.println("Success rate = " + data + "/" + len + " (" + successRate + "%)");
        System.out.println("Finished: " + dateFormat.format( new Date() ));
    }
}

