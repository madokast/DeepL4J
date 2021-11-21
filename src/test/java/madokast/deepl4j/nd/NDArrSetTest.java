package madokast.deepl4j.nd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * NDArr set 测试
 *
 * @author ZhaoRX
 * @since 2021/11/21 23:02
 */
class NDArrSetTest {

    @Test
    public void test_set() {
        NDArr b = new NDArr(new float[2 * 3 * 4 * 5], new int[]{2, 3, 4, 5});
        b.set(new int[]{1, 1, 0, 3}, 2);
        Log.info(b.toString());
    }
}