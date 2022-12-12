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

/**
 * Tests slice from start of matrix.
 * @author Ron.Coleman
 */
//@FixMethodOrder(MethodSorters.DEFAULT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Dice3Test {
    // TODO: instantiate a concrete IMop here
    IMop mop = new Mop();

    // Matrix is this size to anticipate start, mid, end testing.
    final double[][] TEST_MATRIX = {
            {1}
    };

    final double[][] EXPECTED_MATRIX = {
            {1}
    };
    /**
     * Tests that slice matches expectations.
     */
    @Test
    public void test() {
        final double[][] dice3 = mop.dice(TEST_MATRIX,0,1);

        int numRows = dice3.length;
        assert(numRows == EXPECTED_MATRIX.length);

        int numCols = dice3[0].length;
        assert(numCols == EXPECTED_MATRIX[0].length);

        IntStream.range(0,numRows).forEach( rowno -> {
            IntStream.range(0,numCols).forEach(colno -> {
                assert(dice3[rowno][colno] == EXPECTED_MATRIX[rowno][colno]);
            });
        });

        mop.print(this.getClass().getName()+" dice3",dice3);
    }
}
