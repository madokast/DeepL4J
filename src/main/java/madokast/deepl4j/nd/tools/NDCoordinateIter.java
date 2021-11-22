package madokast.deepl4j.nd.tools;

import madokast.deepl4j.nd.NDArr;

import java.util.Arrays;
import java.util.Iterator;

public class NDCoordinateIter implements Iterator<int[]>, Iterable<int[]> {

    private final int[] currentCoo;

    private final int[] max;

    private final int[] shape;

    public NDCoordinateIter(final int[] shape) {
        this.shape = shape;
        this.currentCoo = new int[this.shape.length];
        currentCoo[currentCoo.length - 1] = -1;

        this.max = Arrays.copyOf(shape, shape.length);
        for (int i = 0; i < max.length; i++) {
            max[i]--;
        }
    }

    @Override
    public boolean hasNext() {
        return !Arrays.equals(currentCoo, max);
    }

    @Override
    public int[] next() {
        addOne(currentCoo);
        return currentCoo;
    }

    @Override
    public Iterator<int[]> iterator() {
        return this;
    }

    /**
     * @param number 被加数
     * @return 是否溢出
     */
    private boolean addOne(int[] number) {
        int length = number.length;
        int carry = 0;
        number[length - 1]++;

        for (int i = length - 1; i >= 0; i--) {
            int digit = number[i] + carry;
            int r = shape[i];
            if (digit == r) {
                number[i] = 0;
                carry = 1;
            } else if (digit > r) {
                throw new RuntimeException("代码有误，加一不至于出现这种情况");
            } else {
                number[i] = digit;
                carry = 0;
                break;
            }
        }

        return carry == 1;
    }
}
