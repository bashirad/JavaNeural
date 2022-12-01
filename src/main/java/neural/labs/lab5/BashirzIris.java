package neural.labs.lab5;

import neural.matrix.IMop;
import neural.matrix.Mop;
import neural.util.EncogHelper;
import neural.util.IrisHelper;
import org.apache.commons.math3.stat.StatUtils;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.mathutil.Equilateral;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.BasicTraining;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static neural.util.EncogHelper.*;

/**
 * XOR: This example is essentially the "Hello World" of neural network
 * programming. This example shows how to construct an Encog neural network to
 * predict the report from the XOR operator. This example uses backpropagation
 * to train the neural network.
 *
 * This example attempts to use a minimum of Encog values to create and train
 * the neural network. This allows you to see exactly what is going on. For a
 * more advanced example, that uses Encog factories, refer to the XORFactory
 * example.
 *
 * The original version of this code does not appear to converge. I fixed this
 * problem by using two neurons in the hidden layer and instead of ramped activation,
 * sigmoid activation. This makes the network reflect the model in figure 1.1
 * in the book, d. 11. I also added more comments to make the code more explanatory.
 * @author Bashir Dahir
 * @date 21 Sep 2022
 */
public class BashirzIris {
    /**
     * These learning parameters generally give good results according to literature,
     * that is, the training algorithm converges with the tolerance below.
     * */
    public final static double LEARNING_RATE = 0.25;
    public final static double LEARNING_MOMENTUM = 0.25;

    final static double NORMALIZED_HI = 1;
    final static double NORMALIZED_LO = -1;

    final static Map<Integer, NormalizedField> normalizers = new HashMap<>();

    /** Inputs and ideals necessary for training. */
    public static double[][] TRAINING_INPUTS;
    public static double[][] TRAINING_IDEALS;

    /** Inputs and ideals necessary for testing. */
    public static double[][] TESTING_INPUTS;
    public static double[][] TESTING_IDEALS;

    /** this function normalizes the data */
    static double[][] normalize(double[][] src) {

        int rowCount = src.length;
        int colCount = src[0].length;

        IMop mop = new Mop();

        double [][]dest = new double[colCount][rowCount];
        double [] MaxArr = new double[150];
        double [] MinArr = new double[150];

        IntStream.range(0,rowCount).forEach(rowno -> {
            IntStream.range(0, colCount).forEach(colno -> {
                double MAX = StatUtils.max(mop.transpose(src)[colno], 0, rowCount);
                double MIN = StatUtils.min(mop.transpose(src)[colno], 0, rowCount);

                MaxArr[colno] = MAX;
                MinArr[colno] = MIN;
                // Normalize values with an actual range of (max to min) to (-1 to 1)
                NormalizedField norm = new NormalizedField(NormalizationAction.Normalize,
                        null, MAX, MIN, NORMALIZED_HI, NORMALIZED_LO);

                dest[colno][rowno] = norm.normalize(mop.transpose(src)[colno][rowno]);
                normalizers.put((int) norm.normalize(colno), norm);
                normalizers.put((int) norm.deNormalize(colno), norm);
            });
        });
        /**
         * Print the Max and Min
         */
        System.out.println(" ----- training inputs ");
        System.out.println("SL: " + MaxArr[0] + " - " + MinArr[0]);
        System.out.println("SW: " + MaxArr[1] + " - " + MinArr[1]);
        System.out.println("PL: " + MaxArr[2] + " - " + MinArr[2]);
        System.out.println("PW: " + MaxArr[3] + " - " + MinArr[3]);
        return mop.transpose(dest);
    }

    static final Equilateral eq =
            new Equilateral(IrisHelper.species2Cat.size(),
                    NORMALIZED_HI,
                    NORMALIZED_LO);

    /* Encode */
    static double[][] encode(double[][] src) {
        int rowCount = src.length;
        int colCount = src[0].length;

        double[][] dest = new double[rowCount][colCount];

        IntStream.range(0, rowCount).forEach(rowno -> {
            dest[rowno] = eq.encode(eq.decode(src[rowno]));
        });
        return dest;
    }

    public static void init() {
        double[][] observations =
                IrisHelper.load("data/iris.csv");

        IMop mop = new Mop();

        /**
         * Transpose observations so that the
         * result is 2D array in a row-major order
         * */
        double [][] transposedObservations = mop.transpose(observations);
        double[][] transposedObservations_ = mop.dice(transposedObservations,0,4);
        double[][] inputs = normalize(transposedObservations_);
        mop.printNormalized(transposedObservations_, "->", inputs);

        double[][] transposedObservations2_ = mop.dice(transposedObservations, 4, 5);
        double[][] outputs = encode(transposedObservations2_);
        System.out.println(" ----- training outputs----------------");
        mop.printEncoded(outputs, "", transposedObservations2_);

        /**
         * Test Print
         * observations are not working. Use Transposed Observations
         * Diced Transposed Observations doesn't include the last column which has only 0's,1's and 2's
         */

        TRAINING_INPUTS = mop.slice(inputs,0,120);
        TESTING_INPUTS = mop.slice(inputs, 120, 150);

        TRAINING_IDEALS = mop.slice(outputs,0,120);
        TESTING_IDEALS = mop.slice(outputs,120,150);

        report("training", TRAINING_INPUTS, TRAINING_IDEALS);
        report("testing",TESTING_INPUTS, TESTING_IDEALS);
    }
    /**
     * The report method.
     * it outputs the results
     * of the normalization and normalization..
     */
    static void report(String s, double [][] TRAIN_INP, double[][] TRAIN_IDE){

    }

    /**
     * The main method.
     * @param args No arguments are used.
     */
    public static void main(final String args[]) {
        // load Iris data and normalize it
        init();

        // Instantiate the network
        BasicNetwork network = new BasicNetwork();

        // Input layer plus bias node
        network.addLayer(new BasicLayer(null, true, 2));

        // Hidden layer plus bias node
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 2));

        // Output layer
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));

        // No more layers to be added
        network.getStructure().finalizeStructure();

        // Randomize the weights
        network.reset();

        EncogHelper.describe(network);

        // Create training observations
        MLDataSet trainingSet = new BasicMLDataSet(TRAINING_INPUTS, TRAINING_IDEALS);

        // Use a training object for the learning algorithm, backpropagation.
//        final BasicTraining training = new Backpropagation(network, trainingSet,LEARNING_RATE,LEARNING_MOMENTUM);
        final BasicTraining training = new ResilientPropagation(network, trainingSet);

        // Set learning batch size: 0 = batch, 1 = online, n = batch size
        // See org.encog.neural.networks.training.BatchSize
        // train.setBatchSize(0);

        int epoch = 0;

        double minError = Double.MAX_VALUE;

        double error = 0.0;

        int sameCount = 0;
        final int MAX_SAME_COUNT = 5*LOG_FREQUENCY;

        EncogHelper.log(epoch, error,false, false);
        do {
            training.iteration();

            epoch++;

            error = training.getError();

            if(error < minError) {
                minError = error;
                sameCount = 1;
            }
            else
                sameCount++;

            if(sameCount > MAX_SAME_COUNT)
                break;

            EncogHelper.log(epoch, error,false,false);

        } while (error > TOLERANCE && epoch < MAX_EPOCHS);

        training.finishTraining();

        EncogHelper.log(epoch, error,sameCount > MAX_SAME_COUNT, true);
        EncogHelper.report(trainingSet, network);
        EncogHelper.describe(network);

        Encog.getInstance().shutdown();
    }
}
