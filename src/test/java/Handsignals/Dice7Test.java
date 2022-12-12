/*
 * Copyright (c) Ron Coleman
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package Handsignals;

import neural.labs.lab3.Mop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import neural.matrix.IMop;
import java.util.stream.IntStream;

/* Tests slice from start of matrix.
 * @author Ron.Coleman
 */
//@FixMethodOrder(MethodSorters.DEFAULT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Dice7Test {
    // TODO: instantiate a concrete IMop here
    IMop mop = new Mop();

    // Matrix is this size to anticipate start, mid, end testing.
    final double[][] TEST_MATRIX = {
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

    final double[][] EXPECTED_MATRIX ={
            {10},
            {20},
            {30},
            {40},
            {50},
            {60},
            {70},
            {80},
            {90},
            {100}
    };
    /* Tests that slice matches expectations.
     */
    @Test
    public void test() {
        final double[][] dice7 = mop.dice(TEST_MATRIX,9,10);

        int numRows = dice7.length;
        assert(numRows == EXPECTED_MATRIX.length);

        int numCols = dice7[0].length;
        assert(numCols == EXPECTED_MATRIX[0].length);

        IntStream.range(0,numRows).forEach(rowno -> {
            IntStream.range(0,numCols).forEach(colno -> {
                assert(dice7[rowno][colno] == EXPECTED_MATRIX[rowno][colno]);
            });
        });

        mop.print(this.getClass().getName()+" dice7",dice7);
    }
}