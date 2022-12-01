package neural.labs.lab7.mnist;


import neural.labs.lab7.MLoader;
import neural.matrix.IMop;
import neural.matrix.Mop;
import neural.mnist.IMLoader;
import neural.util.EncogHelper;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.BasicTraining;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static neural.util.EncogHelper.*;

public class BashirzMTraining {

    public final static int NUM_SAMPLES = 2000;

    /**
     * Inputs and ideals necessary for training.
     */
    public static double[][] TRAINING_INPUTS;
    public static double[][] TRAINING_IDEALS;


    private static final DecimalFormat decimalFormat1 = new DecimalFormat("00.0");
    static SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");


    public static void init() {

        String pixelPath = "C:\\Users\\Bashir\\Documents\\Bashirs_Code_all\\Java\\BashirzJavaNeural\\data\\train-images.idx3-ubyte";
        String labelPath = "C:\\Users\\Bashir\\Documents\\Bashirs_Code_all\\Java\\BashirzJavaNeural\\data\\train-labels.idx1-ubyte";
        MLoader loader = new MLoader(pixelPath, labelPath);
        loader.load();


        IMLoader.Normal normal = loader.normalize();

        IMop mop = new Mop();

        TRAINING_INPUTS = mop.slice(normal.pixels(), 0, NUM_SAMPLES);
        assert (TRAINING_INPUTS[0].length == (28 * 28));

        TRAINING_IDEALS = mop.slice(normal.labels(), 0, NUM_SAMPLES);
        assert (TRAINING_IDEALS[0].length == (10 - 1));
    }

    public static void main(final String[] args) {

        init();

        BasicNetwork network = new BasicNetwork();

        network.addLayer(new BasicLayer(null, true, 784));

        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 100));

        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 75));

        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 9));

        network.getStructure().finalizeStructure();

        network.reset();

        EncogHelper.summarize(network);

        MLDataSet trainingSet = new BasicMLDataSet(TRAINING_INPUTS, TRAINING_IDEALS);

        final BasicTraining training = new ResilientPropagation(network, trainingSet);

        int epoch = 0;

        double minError = Double.MAX_VALUE;

        double error = 0.0;

        int sameCount = 0;

        final int MAX_SAME_COUNT = 5 * LOG_FREQUENCY;

        String directory = "C:\\Users\\Bashir\\Documents\\Bashirs_Code_all\\Java\\BashirzJavaNeural\\data";

        EncogHelper.log(epoch, error, false, false);
        do {
            training.iteration();

            epoch++;

            error = training.getError();

            if (error < minError) {
                minError = error;
                sameCount = 1;

                EncogDirectoryPersistence.saveObject(new File(directory + "/encogmnist-" + NUM_SAMPLES + ".bin"),network);
            } else
                sameCount++;

            if (sameCount > MAX_SAME_COUNT)
                break;

            EncogHelper.log(epoch, error, false, false);

        } while (error > TOLERANCE && epoch < MAX_EPOCHS);

        training.finishTraining();

        EncogHelper.log(epoch, error, sameCount > MAX_SAME_COUNT, true);
        EncogHelper.summarize(network);

        Encog.getInstance().shutdown();

        MExercise ex = new MExercise(network, trainingSet);
        int len = trainingSet.size();
        int data = ex.report().data();

        String successRate = decimalFormat1.format((( float )data / ( float ) len ) * 100 );
        System.out.println("Success rate = " + data + "/" + len + " (" + successRate + "%)");
        System.out.println("Finished: " + dateFormat.format( new Date() ));
    }


}

