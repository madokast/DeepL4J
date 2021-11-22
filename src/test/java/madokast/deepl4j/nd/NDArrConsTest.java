package madokast.deepl4j.nd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NDArr 构造测试
 *
 * @author ZhaoRX
 * @since 2021/11/21 23:02
 */
class NDArrConsTest {

    @Test
    public void test_cons() {
        NDArr one = NDArr.of(new float[]{1});
        Log.info(one.toString());
    }

    @Test
    public void test_cons2() {
        NDArr a12 = NDArr.of(new float[]{1, 2});
        Log.info(a12.toString());
    }

    @Test
    public void test_cons3() {
        NDArr a123 = NDArr.of(new float[]{1, 2, 3});
        Log.info(a123.toString());
    }

    @Test
    public void test_cons4() {
        NDArr a12 = NDArr.of(new float[][]{{1}, {2}});
        Log.info(a12.toString());
    }

    @Test
    public void test_cons5() {
        NDArr a123 = NDArr.of(new float[][]{{1}, {2}, {3}});
        Log.info(a123.toString());
    }

    @Test
    public void test_cons6() {
        NDArr a1234 = NDArr.of(new float[][]{{1, 2}, {3, 4}});
        Log.info(a1234.toString());
    }

    @Test
    public void test_cons7() {
        NDArr a1234 = NDArr.of(new float[][]{{1, 2, 3, 3}, {3, 4, 5, 5}});
        Log.info(a1234.toString());
    }

    @Test
    public void test_cons8() {
        NDArr a1234 = NDArr.of(new float[][]{{1, 2}, {3, 4}, {1, 2}, {1, 2}, {3, 4}, {1, 2}});
        Log.info(a1234.toString());
    }

    @Test
    public void test_con9() {
        NDArr b = NDArr.of(new float[][]{{1}});
        Log.info(b.toString());
    }

    @Test
    public void test_con10() {
        NDArr b = NDArr.of(new float[][][]{{{1}}});
        Log.info(b.toString());
    }

    @Test
    public void test_con11() {
        NDArr b = NDArr.of(new float[][][]{{{1}, {2}, {3}}});
        Log.info(b.toString());
    }

    @Test
    public void test_con12() {
        NDArr b = NDArr.of(new float[][][]{
                {{1, 2}, {3, 4}},
                {{5, 6}, {7, 8}},
                {{9, 0}, {2, 1}}
        });
        Log.info(b.toString());
        Log.info("size = " + b.size());
        Log.info("shape = " + Arrays.toString(b.shape()));
    }

    @Test
    public void test_cons13() {
        NDArr b = new NDArr(new float[2 * 3 * 4 * 5], new int[]{2, 3, 4, 5});
        Log.info(b.toString());
    }

    @Test
    public void test_cons14() {
        NDArr b = new NDArr(new float[]{1, 2, 3, 4, 5}, new int[]{1, 5});
        Log.info(b.toString());
    }
}