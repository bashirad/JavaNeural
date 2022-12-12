package Handsignals;

import neural.labs.lab3.Mop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class A {
    // TODO: instantiate a concrete Mop here
    Mop mop = new Mop();

    // Matrix is this size to anticipate start, mid, end testing.
    final double[][] fiveByThree = {
            { 1,  2,  3},
            { 4,  5,  6},
            { 7,  8,  9},
            {10, 11, 12},
            {13, 14, 15}
    };

    final double[][] oneByOne = {
            { 1 }
    };

    final double[][] oneByFive = {
            { 1,  2,  3, 4, 5}
    };

    final double[][] sixBySix  = {
            { 1,  2,  3, 4, 5, 6 },
            { 7, 8, 9, 10, 11, 12 },
            { 13, 14, 15, 16, 17, 18 },
            { 19, 20, 21, 22, 23, 24 },
            { 25, 26, 27, 28, 29, 30 },
            { 31, 32, 33, 34, 35, 36 }
    };

    final double[][] threeByFive = {
            { 1,  2,  3, 4, 5},
            { 6, 7, 8, 9, 10 },
            { 11, 12, 13, 14, 15 }
    };

    final double[][] tenByTen = {
            {1,2,3,4,5,6,7,8,9,10},
            {11,12,13,14,15,16,17,18,19,20},
            {21,22,23,24,25,26,27,28,29,30},
            {31,32,33,34,35,36,37,38,39,40},
            {41,42,43,44,45,46,47,48,49,50},
            {51,52,53,54,55,56,57,58,59,60},
            {61,62,63,64,65,66,67,68,69,70},
            {71,72,73,74,75,76,77,78,79,80},
            {81,82,83,84,85,86,87,88,89,90},
            {91,92,93,94,95,96,97,98,99,100}
    };

    public String testIfEqual(double[][] arr, double[][] expected) {

        if (arr.length != expected.length || arr[0].length != expected[0].length) {
            return "Failed!";
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != expected[i][j]){
                    return "Failed!";
                }
            }
        }
        return "Success!";
    }

    //Write the expected output, then use if statement to check if the expected output is correct

    @Test
    public void test() {
        final double[][] slice1 = mop.slice(fiveByThree,0,2);
        //mop.print("test0",slice1);
        double[][] expectedOutput0 = {{1,2,3},{4,5,6}};
        System.out.println(testIfEqual(slice1,expectedOutput0));


        final double[][] slice2 = mop.slice(fiveByThree,1,3);
        //mop.print("test1",slice2);
        double[][] expectedOutput1 = {{4,5,6},{7,8,9}};
        System.out.println(testIfEqual(slice2,expectedOutput1));

        final double[][] slice3 = mop.slice(oneByOne,0,1);
        //mop.print("test2",slice3);
        double[][] expectedOutput2 = {{1}};
        System.out.println(testIfEqual(slice3,expectedOutput2));

        final double[][] transpose1 = mop.transpose(fiveByThree);
       // mop.print("test3",transpose3);
        double[][] expectedOutput3 = {{1,4,7,10,13},{2,5,8,11,14},{3,6,9,12,15}};
        System.out.println(testIfEqual(transpose1,expectedOutput3));

        final double[][] transpose2 = mop.transpose(oneByFive);
        //mop.print("test4",transpose4);
        double[][] expectedOutput4 = {{1},{2},{3},{4},{5}};
        System.out.println(testIfEqual(transpose2,expectedOutput4));

        final double[][] transpose3 = mop.transpose(oneByOne);
        //mop.print("test5",transpose3);
        double[][] expectedOutput5 = {{1}};
        System.out.println(testIfEqual(transpose3,expectedOutput5));

        final double[][] dice1 = mop.dice(fiveByThree,0,2);
        //mop.print("test6", dice1);
        double[][] expectedOutput6 = {{1,2},{4,5},{7,8},{10,11},{13,14}};
        System.out.println(testIfEqual(dice1,expectedOutput6));


        final double[][] dice2 = mop.dice(fiveByThree,1,3);
        //mop.print("test7", dice2);
        double[][] expectedOutput7 = {{2,3},{5,6},{8,9},{11,12},{14,15}};
        System.out.println(testIfEqual(dice2,expectedOutput7));

        final double[][] dice3 = mop.dice(oneByOne,0,1);
        //mop.print("test8", dice3);
        double[][] expectedOutput8 = {{1}};
        System.out.println(testIfEqual(dice3,expectedOutput8));

        final double[][] dice4 = mop.dice(sixBySix,1,4);
        //mop.print("test9", dice4);
        double[][] expectedOutput9 = {{2,3,4},{8,9,10},{14,15,16},{20,21,22},{26,27,28},{32,33,34}};
        System.out.println(testIfEqual(dice4,expectedOutput9));

        final double[][] dice5 = mop.dice(sixBySix,4,5);
        //mop.print("test10", dice5);
        double[][] expectedOutput10 = {{5},{11},{17},{23},{29},{35}};
        System.out.println(testIfEqual(dice5,expectedOutput10));

        final double[][] slice4 = mop.slice(sixBySix,1,4);
        //mop.print("test11",slice4);
        double[][] expectedOutput11 = {{7,8,9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24}};
        System.out.println(testIfEqual(slice4,expectedOutput11));

        final double[][] slice5 = mop.slice(sixBySix,4,5);
        //mop.print("test12",slice5);
        double[][] expectedOutput12 = {{25, 26, 27, 28, 29, 30}};
        System.out.println(testIfEqual(slice5,expectedOutput12));

        final double[][] slice6 = mop.slice(threeByFive,2,3);
        //mop.print("test13",slice6);
        double[][] expectedOutput13 = {{11, 12, 13, 14, 15}};
        System.out.println(testIfEqual(slice6,expectedOutput13));

        final double[][] slice7 = mop.slice(threeByFive,0,1);
        //mop.print("test14",slice7);
        double[][] expectedOutput14 = {{1,2,3,4,5}};
        System.out.println(testIfEqual(slice7,expectedOutput14));

        final double[][] slice8 = mop.slice(tenByTen,6,9);
        //mop.print("test15",slice8);
        double[][] expectedOutput15 = {{61,62,63,64,65,66,67,68,69,70},{71,72,73,74,75,76,77,78,79,80},{81,82,83,84,85,86,87,88,89,90}};
        System.out.println(testIfEqual(slice8,expectedOutput15));

        final double[][] transpose4 = mop.transpose((tenByTen));
        double[][] expectedOutput16 = {{1,11,21,31,41,51,61,71,81,91},{2,12,22,32,42,52,62,72,82,92},{3,13,23,33,43,53,63,73,83,93},{4,14,24,34,44,54,64,74,84,94},{5,15,25,35,45,55,65,75,85,95},{6,16,26,36,46,56,66,76,86,96},{7,17,27,37,47,57,67,77,87,97},{8,18,28,38,48,58,68,78,88,98},{9,19,29,39,49,59,69,79,89,99},{10,20,30,40,50,60,70,80,90,100}};
        System.out.println(testIfEqual(transpose4,expectedOutput16));

        final double[][] transpose5 = mop.transpose(threeByFive);
        //mop.print("test17",transpose5);
        double[][] expectedOutput17 = {{1,6,11},{2,7,12},{3,8,13},{4,9,14},{5,10,15}};
        System.out.println(testIfEqual(transpose5,expectedOutput17));

        final double[][] transpose6 = mop.transpose(sixBySix);
        //mop.print("test18",transpose6);
        double[][] expectedOutput18 = {{1,7,13,19,25,31},{2,8,14,20,26,32},{3,9,15,21,27,33},{4,10,16,22,28,34},{5,11,17,23,29,35},{6,12,18,24,30,36}};
        System.out.println(testIfEqual(transpose6,expectedOutput18));

        final double[][] dice6 = mop.dice(tenByTen,0,3);
        //mop.print("test19", dice6);
        double[][] expectedOutput19 = {{1,2,3},{11,12,13},{21,22,23},{31,32,33},{41,42,43},{51,52,53},{61,62,63},{71,72,73},{81,82,83},{91,92,93}};
        System.out.println(testIfEqual(dice6,expectedOutput19));

        final double[][] dice7 = mop.dice(tenByTen,9,10);
        //mop.print("test20", dice7);
        double[][] expectedOutput20 = {{10},{20},{30},{40},{50},{60},{70},{80},{90},{100}};
        System.out.println(testIfEqual(dice7,expectedOutput20));

    }
}
