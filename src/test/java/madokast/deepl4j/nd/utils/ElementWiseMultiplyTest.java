package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementWiseMultiplyTest {

    @Test
    public void test_mul() {
        NDArr a = NDArrFactory.array(1, 2, 3, 4);
        NDArr b = NDArrFactory.array(10, 20, 30, 40);
        NDArr m = NDArrFactory.array(10, 40, 90, 160);
        NDArr c = ElementWiseFunc.multiply(a, b);
        Log.info(c);
        assertEquals(m, c);
    }

    @Test
    public void test() {
        NDArr a = NDArrFactory.range(9).reShape(3, 3);
        NDArr b = ElementWiseFunc.multiply(NDArrFactory.ones(3), 10);
        Log.info(a);
        Log.info(b);
    }

    @Test
    public void test2() {
        NDArr a = NDArrFactory.range(9).reShape(3, 3);
        NDArr b = ElementWiseFunc.multiply(NDArrFactory.ones(3), 10);
        NDArr r = ElementWiseFunc.add(a, b);
        NDArr e = NDArrFactory.range(10, 19).reShape(3, 3);
        assertEquals(e, r);
    }

    @Test
    public void test3() {
        NDArr a = NDArrFactory.range(9).reShape(3, 3);
        NDArr b = ElementWiseFunc.multiply(NDArrFactory.ones(3), 10);
        NDArr r = ElementWiseFunc.subtract(a, b);
        NDArr e = NDArrFactory.range(-10, -1).reShape(3, 3);
        assertEquals(e, r);
    }

    @Test
    public void test4() {
        NDArr a = NDArrFactory.range(9).reShape(3, 3);
        NDArr b = ElementWiseFunc.multiply(NDArrFactory.ones(3), 10);
        NDArr r = ElementWiseFunc.multiply(a, b);
        NDArr e = NDArrFactory.range(0, 90, 10).reShape(3, 3);
        assertEquals(e, r);
    }

    @Test
    public void test5() {
        NDArr a = NDArrFactory.range(9).reShape(3, 3);
        NDArr b = ElementWiseFunc.multiply(NDArrFactory.ones(3), 10);
        NDArr r = ElementWiseFunc.divide(a, b);
        NDArr e = NDArrFactory.linSpace(0, 0.8f, 9).reShape(3, 3);
        assertEquals(e, r);
    }
}