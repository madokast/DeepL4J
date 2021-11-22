package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NDArrFactoryRangeTest {

    @Test
    public void test1() {
        NDArr range = NDArrFactory.range(5);
        NDArr array = NDArrFactory.array(0, 1, 2, 3, 4);
        Log.info(range);
        assertEquals(array, range);
    }

    @Test
    public void test2() {
        NDArr range = NDArrFactory.range(2, 5);
        NDArr array = NDArrFactory.array(2, 3, 4);
        Log.info(range);
        assertEquals(array, range);
    }

    @Test
    public void test3() {
        NDArr range = NDArrFactory.range(1, 10, 2);
        NDArr array = NDArrFactory.array(1, 3, 5, 7, 9);
        Log.info(range);
        assertEquals(array, range);
    }
}