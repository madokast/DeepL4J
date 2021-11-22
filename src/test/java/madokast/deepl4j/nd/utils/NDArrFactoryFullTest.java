package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NDArrFactoryFullTest {

    @Test
    public void test(){
        NDArr full = NDArrFactory.full(Float.POSITIVE_INFINITY, 2, 2);
        Log.info(full);
    }

    @Test
    public void test10(){
        NDArr full = NDArrFactory.full(10, 2, 2);
        Log.info(full);
    }
}