package neural.matrix;

import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;

public interface IMop {
    double[][] slice(double[][] src,int startRow,int endRow);
    double[][] transpose(double[][] src);
    double[][] dice(double[][] src,int startCol, int endCol);
    void print(String msg, double[][] src);
    void printNormalized(double[][] src1, String message, double[][] src2);
    void printEncoded(double[][] src1, String message, double[][] src2);

    public void reportIdeals(MLDataSet trainingSet, BasicNetwork network);
    public String idealType (double num);
    public String actualType (double y1, double y2);
    public double distanceFormula (double x1, double x2, double y1, double y2);
}
