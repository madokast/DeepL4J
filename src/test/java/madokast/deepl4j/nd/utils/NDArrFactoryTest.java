package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NDArrFactoryTest {

    @Test
    public void test_zeros() {
        NDArr empty = NDArrFactory.zeros(2);
        Log.info(empty);
    }

    @Test
    public void test_zeros2() {
        NDArr empty = NDArrFactory.zeros(3);
        Log.info(empty);
    }

    @Test
    public void test_zeros3() {
        NDArr empty = NDArrFactory.zeros(3, 1);
        Log.info(empty);
    }


    @Test
    public void test_zeros4() {
        NDArr empty = NDArrFactory.zeros(2, 3, 4);
        Log.info(empty);
    }

    @Test
    public void test_empty() {
        NDArr empty = NDArrFactory.empty(2, 2);
        Log.info(empty);
    }

    @Test
    public void test_eye() {
        NDArr eye = NDArrFactory.eye(4);
        Log.info(eye);
    }

    @Test
    public void test_ones() {
        NDArr empty = NDArrFactory.ones(2);
        Log.info(empty);
    }

    @Test
    public void test_ones2() {
        NDArr empty = NDArrFactory.ones(3);
        Log.info(empty);
    }

    @Test
    public void test_ones3() {
        NDArr empty = NDArrFactory.ones(3, 1);
        Log.info(empty);
    }


    @Test
    public void test_ones4() {
        NDArr empty = NDArrFactory.ones(2, 3, 4);
        Log.info(empty);
    }

}