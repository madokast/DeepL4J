package madokast.deepl4j.nd.tools;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NDCoordinateIterTest {

    @Test
    public void test() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5});
        NDCoordinateIter iter = arr.coordinateIter();
        for (int[] anInt : iter) {
            Log.info(Arrays.toString(anInt));
        }
    }

    @Test
    public void test2() {
        NDCoordinateIter iter = new NDCoordinateIter(new int[]{5});
        int[] next = iter.next();
        Log.info(Arrays.toString(next));
        next = iter.next();
        Log.info(Arrays.toString(next));
        next = iter.next();
        Log.info(Arrays.toString(next));
    }

    @Test
    public void test3() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5, 6});
        arr.reShape(2, 3);
        NDCoordinateIter iter = arr.coordinateIter();
        for (int[] anInt : iter) {
            Log.info(Arrays.toString(anInt));
        }
    }

    @Test
    public void test4() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5, 6});
        arr.reShape(3, 2);
        NDCoordinateIter iter = arr.coordinateIter();
        for (int[] anInt : iter) {
            Log.info(Arrays.toString(anInt));
        }
    }

    @Test
    public void test5() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5, 6});
        arr.reShape(1, 1, 3, 2);
        Log.info(arr);
        NDCoordinateIter iter = arr.coordinateIter();
        for (int[] anInt : iter) {
            Log.info(Arrays.toString(anInt));
        }
    }

    @Test
    public void test6() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5, 6});
        arr.reShape(1, 1, 3, 2);
        Log.info(arr);
        NDCoordinateIter iter = arr.coordinateIter();
        for (int[] anInt : iter) {
            Log.info(Arrays.toString(anInt) + " -> " + arr.at(anInt));
        }
    }
}