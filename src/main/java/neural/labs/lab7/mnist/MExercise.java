package neural.labs.lab7.mnist;

import org.encog.mathutil.Equilateral;
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
        Equilateral eq = new Equilateral(10, 1, 0);
        double num, label;
        for(int i = 0; i < set.size(); i++){
            label = eq.decode(set.get(i).getIdealArray());
            num = eq.decode(network.compute(set.get(i).getInput()).getData());
            if (label == num){data++;}
        }
        return new Report(len,data);
    }
}
/*
  MLDataPair pair = set.get(i);
  double [] ideals = pair.getIdealArray();
  double [] inputs = pair.getInputArray();
  double [] actuals = network.compute(inputs);
  double decodedIdeal = eq.decode(ideals);
  double decodedActual = eq.decode(actuals);
  working on GPUs
  refresh on C code
  BCG code
  */