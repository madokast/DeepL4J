package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NDArrLisSpaceTest {

    @Test
    public void test() {
        NDArr ndArr = NDArrFactory.linSpace(1, 1, 10);
        Log.info(ndArr);
    }

    @Test
    public void test2() {
        NDArr ndArr = NDArrFactory.linSpace(1, 1, 10);
        Log.info(ndArr.reShape(10, 1));
    }

    @Test
    public void test3() {
        NDArr ndArr = NDArrFactory.linSpace(1, 5, 3);
        Log.info(ndArr);
    }
}