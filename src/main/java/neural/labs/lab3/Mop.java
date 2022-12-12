package neural.labs.lab3;

import neural.matrix.IMop;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Mop implements IMop {

    public double[][] slice(double[][] src, int startRow, int endRow) {
        if (startRow >= endRow || startRow < 0 || endRow > src.length) {
            throw new RuntimeException();
        }
        double[][] newArr = new double[endRow - startRow][src[0].length];
        int index = 0;
        for (int i = startRow; i < endRow; i++) {
            newArr[index] = src[i];
            index++;
        }
        return newArr;
    }

    public double[][] transpose(double[][] src) {
        double[][] newArr = new double[src[0].length][src.length];
        for (int i = 0; i < src[0].length; i++) {
            for (int j = 0; j < src.length; j++) {
                newArr[i][j] = src[j][i];
            }
        }
        return newArr;
    }

    public double[][] dice(double[][] src, int startCol, int endCol) {
        if (startCol >= endCol || startCol < 0 || endCol > src[0].length) {
            throw new RuntimeException();
        }
        double[][] newArr = new double[src.length][endCol - startCol];
        for (int i = 0; i < src.length; i++) {
            newArr[i] = Arrays.copyOfRange(src[i],startCol,endCol);
        }
        return newArr;
    }

    public void print(String msg, double[][] src) {
        System.out.println(msg);
        for (double[] x : src)
        {
            for (double y : x)
            {
                System.out.print(y + ", ");
            }
            System.out.println();
        }
    }
    public void printNormalized(double [][] src1, String caption,double[][] src2) {
        int rowCount = src2.length;
        int colCount = src2[0].length;

        System.out.printf("%3s %3s %13s %3s %13s %3s %13s %3s %13s \n", "#", "SL", "|", "SW", "|", "PL", "|", "PW", "|");

        IntStream.range(0,rowCount).forEach(rowno -> {
            System.out.printf("%3d",rowno);
            IntStream.range(0, colCount).forEach(colno ->
                    System.out.printf(" %,5.1f %s %,5.1f %2s", src1[rowno][colno], caption, src2[rowno][colno], "|" ));
            System.out.println("");
        });
    }
    public void printEncoded(double [][] src1, String caption,double[][] src2) {
        int rowCount = src2.length;
        int colCount = src2[0].length;

        System.out.printf("%3s %3s %7s %13s \n", "#", "t1", "t2", "Decoding");

        IntStream.range(0,rowCount).forEach(rowno -> {
            System.out.printf("%3d",rowno);
            IntStream.range(0, colCount).forEach(colno ->
                    System.out.printf(" %,7.4f %,7.4f %,3.0f %-3s %-11s \n", src1[rowno][colno], src1[rowno][colno+1],
                            src2[rowno][colno], "->", this.flowerType(src2[rowno][colno])));
        });
    }
    public String flowerType (double num) {
        switch ((int)num) {
            case 0:
                return "Setosa";
            case 1:
                return "Virginica";
            case 2:
                return "Versicolor";
        }
        return null;
    }
    /**
     * Reports training results.
     * @param trainingSet Training set of observations
     * @param network     Network
     */
    public void reportIdeals(MLDataSet trainingSet, BasicNetwork network) {
        System.out.println("Network training results:");

        int sz = trainingSet.size();
        if(sz == 0)
            return;

        MLDataPair first = trainingSet.get(0);

        // Output xs header
        System.out.printf("%4s ","#");

        // Output ts (ideals) header
        int szOutputs = first.getIdealArray().length;
        System.out.printf("%7s ","Ideal");

        // Output ys (actuals) header
        System.out.printf("%15s ","Actual");

        System.out.println();

        // Report inputs and ideals vs. outputs.
        int n = 1;
        for (MLDataPair pair : trainingSet) {
            System.out.printf("%4d ",n);

            final MLData inputs = pair.getInput();
            final MLData outputs = network.compute(inputs);

            final MLData ideals = pair.getIdeal();
            final double ideal[] = ideals.getData();
            for(int i = 0; i < ideal.length - 1; i++)
                System.out.print(idealType(ideal[i]));


            final double actual[] = outputs.getData();
            for(int i = 1; i < actual.length; i+= 2)
                System.out.print(actualType(actual[i-1],actual[i]));

            System.out.println("");

            n += 1;
        }
    }

    public String idealType (double num) {
        switch ((int)Math.round(num)) {
            case 0:
                return "Setosa";
            case 1:
                return "Virginica";
            case -1:
                return "Versicolor";
        }
        return null;
    }

    public String actualType (double y1, double y2) {
        double Setosa1 = 0.0000;
        double Setosa2 = 1.000;
        double Virginica1 = 0.8660;
        double Virginica2 = -0.5000;
        double Versicolor1 = -0.8660;
        double Versicolor2 = -0.5000;

        double dist1 = distanceFormula(Setosa1,y1,Setosa2,y2);
        double dist2 = distanceFormula(Virginica1,y1,Virginica2,y2);
        double dist3 = distanceFormula(Versicolor1,y1,Versicolor2,y2);

        Double[] distances = {dist1, dist2, dist3};

        double min = Collections.min(Arrays.asList(distances));

        if (min == dist1) {
            return "Setosa";
        } else if (min == dist2) {
            return "Virginica";
        } else if (min == dist3) {
            return "Versicolor";
        }
        return null;
    }
    public double distanceFormula (double x1, double x2, double y1, double y2) {
        return (Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
    }


}
