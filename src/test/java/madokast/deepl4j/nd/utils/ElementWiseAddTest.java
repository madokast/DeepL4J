package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.NDArr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementWiseAddTest {

    @Test
    public void test() {
        NDArr a = NDArr.of(new float[]{1, 2, 3});
        NDArr b = NDArr.of(new float[]{5, 6, 1});

        Log.info(a);
        Log.info(b);
        Log.info(ElementWiseFunc.add(a, b));
    }

    @Test()
    public void test2() {
        NDArr a = NDArr.of(new float[]{1, 2, 3});
        NDArr b = NDArr.of(new float[]{5});

        Log.info(a);
        Log.info(b);
        Log.info(ElementWiseFunc.add(a, b));
    }

    @Test()
    public void test3() {
        NDArr a = NDArr.of(new float[][]{{1, 2, 3}, {3, 2, 1}});
        NDArr b = NDArr.of(new float[]{5});

        Log.info(a);
        Log.info(b);
        Log.info(ElementWiseFunc.add(a, b));
    }

    @Test()
    public void test4() {
        NDArr a = NDArr.of(new float[][]{{1, 2, 3}, {3, 2, 1}});
        NDArr b = NDArr.of(new float[]{10, 20, 30});

        Log.info(a);
        Log.info(b);
        Log.info(ElementWiseFunc.add(a, b));
    }

    @Test
    public void test_add() {
        NDArr a = NDArr.of(new float[][]{
                {0, 0, 0},
                {10, 10, 10},
                {20, 20, 20},
                {30, 30, 30},
        });
        NDArr b = NDArrFactory.array(1, 2, 3);

        NDArr expect = NDArr.of(new float[][]{
                {1, 2, 3},
                {11, 12, 13},
                {21, 22, 23},
                {31, 32, 33},
        });

        NDArr add = ElementWiseFunc.add(a, b);
        Log.info(a);
        assertEquals(expect, add);
    }

    @Test
    public void test_add2() {
        NDArr a = NDArr.of(new float[][]{
                {0, 0, 0},
                {10, 10, 10},
                {20, 20, 20},
                {30, 30, 30},
        });
        float b = 5;

        NDArr expect = NDArr.of(new float[][]{
                {5, 5, 5},
                {15, 15, 15},
                {25, 25, 25},
                {35, 35, 35},
        });

        NDArr add = ElementWiseFunc.add(a, b);
        Log.info(add);
        assertEquals(expect, add);
    }

}