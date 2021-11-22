package madokast.deepl4j.nd;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NDArrShapeTest {

    @Test
    public void test_shape() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5, 6});
        Log.info(arr);
        Log.info(Arrays.toString(arr.shape()));
    }


    @Test
    public void test_shape2() {
        NDArr arr = NDArr.of(new float[][]{{1, 2, 3}, {4, 5, 6}});
        Log.info(arr);
        Log.info(Arrays.toString(arr.shape()));
    }

    @Test
    public void test_reShape() {
        NDArr arr = NDArr.of(new float[]{1, 2, 3, 4, 5, 6});
        Log.info(arr);
        Log.info(Arrays.toString(arr.shape()));
        arr.reShape(new int[]{3, 2});
        Log.info(arr);
        Log.info(Arrays.toString(arr.shape()));
    }
}