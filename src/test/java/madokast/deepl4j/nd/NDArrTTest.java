package madokast.deepl4j.nd;

import madokast.deepl4j.nd.utils.NDArrFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class NDArrTTest {

    @Test
    public void test_T() {
        NDArr arr = NDArrFactory.array(1, 2, 3);
        NDArr t = arr.T();
        Log.info(arr);
        Log.info(t);

        assertEquals(arr, t);
    }

    @Test
    public void test_T2() {
        NDArr arr = NDArrFactory.array(1);
        NDArr t = arr.T();
        Log.info(arr);
        Log.info(t);

        assertEquals(arr, t);
    }

    @Test
    public void test_T3() {
        NDArr arr = NDArr.of(new float[][]{
                {1, 2},
                {3, 4},
        });
        NDArr t = arr.T();
        Log.info(arr);
        Log.info(t);

        assertEquals(NDArr.of(new float[][]{
                        {1, 3},
                        {2, 4}}),
                t);
    }

    @Test
    public void test_T4() {
        NDArr arr = NDArr.of(new float[][]{
                {1, 2},
                {3, 4},
                {4, 5},
        });
        NDArr t = arr.T();
        Log.info(arr);
        Log.info(t);

        assertEquals(NDArr.of(new float[][]{
                        {1, 3, 4},
                        {2, 4, 5}}),
                t);
    }
}