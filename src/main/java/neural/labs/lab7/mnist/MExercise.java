package neural.labs.lab7.mnist;

import neural.labs.lab7.mnist.MaxzMTraining;
import org.encog.mathutil.Equilateral;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;

public class MExercise{
    private final MLDataSet set;
    private final BasicNetwork network;
    record Report(int len, int data) {}

    public MExercise(BasicNetwork network, MLDataSet set) {
        this.set = set;
        this.network = network;
    }

    public Report report() {
        int len = set.size();
        int data = 0;
        Equilateral eq = new Equilateral(23, 1, 0);
        double num, label;
        for(int i = 0; i < MaxzMTraining.NUM_SAMPLES; i++){
            label = set.get(i).getIdealArray()[i];
            num = network.compute(set.get(i).getInput()).getData()[i];
            if (label == num){data++;}
        }
        return new Report(len,data);
    }
}