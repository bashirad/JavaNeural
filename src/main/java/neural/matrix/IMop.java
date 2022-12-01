package neural.matrix;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;

public interface IMop {
    double[][] slice(double[][] src, int startRow, int endRow);

    double[][] transpose(double[][] src);

    double[][] dice(double[][] src, int startCol, int endCol);

    void print(String msg, double[][] src);

    void printNormalized(double[][] src1, String msg, double[][] src2);

    void printEncoded(double[][] src1, String msg, double[][] src2);

    String flowerType(double num);

    void reportIdeals(MLDataSet trainingSet, BasicNetwork network);
}