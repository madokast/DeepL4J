package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementWiseSinTest {

    @Test
    public void test_sin() {
        NDArr arr = NDArrFactory.array(0, 30, 45, 60, 90);
        Log.info(arr);
        NDArr arr2 = ElementWiseFunc.multiply(arr, (float) (Math.PI / 180));
        Log.info(ElementWiseFunc.sin(arr2));
    }

    @Test
    public void test_cos() {
        NDArr arr = NDArrFactory.array(0, 30, 45, 60, 90);
        Log.info(arr);
        NDArr arr2 = ElementWiseFunc.multiply(arr, (float) (Math.PI / 180));
        Log.info(ElementWiseFunc.cos(arr2));
    }

    @Test
    public void test_tan() {
        NDArr arr = NDArrFactory.array(0, 30, 45, 60, 90);
        Log.info(arr);
        NDArr arr2 = ElementWiseFunc.multiply(arr, (float) (Math.PI / 180));
        Log.info(ElementWiseFunc.tan(arr2));
    }
}