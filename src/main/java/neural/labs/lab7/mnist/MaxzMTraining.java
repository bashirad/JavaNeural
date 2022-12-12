package neural.labs.lab7.mnist;
import neural.labs.lab3.Mop;
import neural.matrix.IMop;
import neural.mnist.MLetter;
import neural.util.EncogHelper;
import neural.util.JpgReader;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.BasicTraining;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;

import javax.management.loading.MLet;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.IntStream;

import static neural.util.EncogHelper.*;

public class MaxzMTraining extends JpgReader{

    // NUM_SAMPLES from each letter to be included in the training set
    public final static int NUM_SAMPLES = 100;
    public static double [] pixels = new double[784];

    public static int [] labels = new int[24];

    /**
     Inputs and ideals necessary for training.
     */
    public static double[][] TRAINING_INPUTS;
    public static double[][] TRAINING_IDEALS;


    private static final DecimalFormat decimalFormat1 = new DecimalFormat("00.0");

    static SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");

    /**
    accessData takes a data folder and the number of files we want to iterate across
    and returns an array of MLetters corresponding to each file
    */
    public static ArrayList<MLetter> accessData (final File folder, int numFiles) {
        ArrayList<MLetter>  mLetters = new ArrayList<>();

        File[] fileEntry = folder.listFiles();

        int label = encodeLabel((folder.getName()));

        for( int j = 0; j < numFiles; j++) {
            pixels = JpgReader.pixelArray(fileEntry[j].getAbsoluteFile());
            MLetter mLetter = new MLetter(pixels, label);
            mLetters.add(mLetter);
            //System.out.println(mLetter.toString());
        }
        return mLetters;
    }

    /**
    Encode the labels to corresponding integers
    */
    public static int encodeLabel (String letter) {
        int number;
        // switch statement to check size
        switch (letter) {

            case "A": number = 0; break;
            case "B": number = 1; break;
            case "C": number = 2; break;
            case "D": number = 3; break;
            case "E": number = 4; break;
            case "F": number = 5; break;
            case "G": number = 6; break;
            case "H": number = 7; break;
            case "I": number = 8; break;
            case "K": number = 9; break;
            case "L": number = 10; break;
            case "M": number = 11; break;
            case "N": number = 12; break;
            case "O": number = 13; break;
            case "P": number = 14; break;
            case "Q": number = 15; break;
            case "R": number = 16; break;
            case "S": number = 17; break;
            case "T": number = 18; break;
            case "U": number = 19; break;
            case "V": number = 20; break;
            case "W": number = 21; break;
            case "X": number = 22; break;
            case "Y": number = 23; break;
            default: number = 0;
        }
        return number;
    }

    /**
     * this function normalizes the data
     */
    static double [][] normalize(ArrayList<MLetter> src) {

        int rowCount = src.size();
        int colCount = src.get(0).pixels().length;

        double [][] dest = new double [rowCount][];

        IntStream.range(0, rowCount).forEach(rowno -> {
            double [] row = new double[colCount];

            IntStream.range(0, colCount).forEach(colno -> {
                row[colno] = src.get(rowno).pixels()[colno] / 255.0;
            });
            dest[rowno] = row;
        });
        return dest;
    }

    /**
     * startup function
     */
    public static void init () {

        final File testingData = new File("C:\\Users\\maxje\\OneDrive\\Desktop\\Ai-Stuff\\MaxEnglishFinalProject\\data\\Test");

        final File[] testingFiles = testingData.listFiles();

        final int testingFilesLength = testingFiles.length;

        ArrayList<MLetter> mLetters = new ArrayList<>();
        ArrayList<MLetter> tempMLetters;

        for (int i = 0; i < testingFilesLength; ++i) {
            tempMLetters = accessData(testingFiles[i],NUM_SAMPLES);
            for(int j = 0; j < tempMLetters.size(); ++j) {
                mLetters.add(tempMLetters.get(j));
            }
            tempMLetters.clear();
        }

        double[][] normalizedMLetters = normalize(mLetters);

        IMop mop = new Mop();

        TRAINING_INPUTS = mop.slice(normalizedMLetters, 0,NUM_SAMPLES * testingFilesLength);
        assert (TRAINING_INPUTS[0].length == (28 * 28));

        TRAINING_IDEALS = mop.slice(normalizedMLetters, 0,NUM_SAMPLES * testingFilesLength);
        assert (TRAINING_IDEALS[0].length == (24 - 1));




    }

    public static void main ( final String[] args){

        init();

        BasicNetwork network = new BasicNetwork();

        network.addLayer(new BasicLayer(null, true, 784));

        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 100));

        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 75));

        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 23));

        network.getStructure().finalizeStructure();

        network.reset();

        EncogHelper.summarize(network);

        MLDataSet trainingSet = new BasicMLDataSet(TRAINING_INPUTS, TRAINING_IDEALS);

        /*for (int i = 0; i < TRAINING_IDEALS.length; ++i) {
            for (int j = 0; j < TRAINING_IDEALS[0].length; ++j) {
                System.out.print(TRAINING_IDEALS[i][j] + " ");
            }
            System.out.println("");
        }*/

        final BasicTraining training = new ResilientPropagation(network, trainingSet);

        int epoch = 0;

        double minError = Double.MAX_VALUE;

        double error = 0.0;

        int sameCount = 0;

        final int MAX_SAME_COUNT = 5 * LOG_FREQUENCY;

        String directory = "C:\\Users\\maxje\\OneDrive\\Desktop\\Ai-Stuff\\MaxEnglishFinalProject\\data";

        EncogHelper.log(epoch, error, false, false);
        do {
            training.iteration();

            epoch++;

            error = training.getError();

            if (error < minError) {
                minError = error;
                sameCount = 1;

                EncogDirectoryPersistence.saveObject(new File(directory + "/encogmnist-" + NUM_SAMPLES + ".bin"), network);
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

        String successRate = decimalFormat1.format(((float) data / (float) len) * 100);
        System.out.println("Success rate = " + data + "/" + len + " (" + successRate + "%)");
        System.out.println("Finished: " + dateFormat.format(new Date()));
    }


}
