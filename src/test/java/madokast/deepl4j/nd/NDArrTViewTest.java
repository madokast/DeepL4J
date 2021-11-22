package madokast.deepl4j.nd;

import madokast.deepl4j.nd.tools.FloatFormat;
import madokast.deepl4j.nd.utils.NDArrFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NDArrTViewTest {

    @Test
    public void test_T() {
        NDArr arr = NDArrFactory.array(1, 2, 3);
        INDArr t = arr.TView();
        Log.info(arr);
        Log.info(t.toString(FloatFormat.NOOP));
    }

    @Test
    public void test_T2() {
        NDArr arr = NDArrFactory.array(1);
        INDArr t = arr.TView();
        Log.info(arr);
        Log.info(t.toString(FloatFormat.NOOP));
    }

    @Test
    public void test_T3() {
        NDArr arr = NDArr.of(new float[][]{
                {1, 2},
                {3, 4},
        });
        INDArr t = arr.TView();
        Log.info(arr);
        Log.info(t.toString(FloatFormat.NOOP));
    }

    @Test
    public void test_T4() {
        NDArr arr = NDArr.of(new float[][]{
                {1, 2},
                {3, 4},
                {4, 5},
        });
        INDArr t = arr.TView();
        Log.info(arr);
        Log.info(t.toString(FloatFormat.NOOP));
    }
}