package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.Log;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NDArrPositionTest {

    @Test
    void test_reverseSelf1() {
        int[] ints = {1};
        NDArrPosition.reverseSelf(ints);
        Log.info(Arrays.toString(ints));
        assertArrayEquals(new int[]{1}, ints);
    }

    @Test
    void test_reverseSelf2() {
        int[] ints = {1, 2};
        NDArrPosition.reverseSelf(ints);
        Log.info(Arrays.toString(ints));
        assertArrayEquals(new int[]{2, 1}, ints);
    }

    @Test
    void test_reverseSelf3() {
        int[] ints = {1, 2, 3};
        NDArrPosition.reverseSelf(ints);
        Log.info(Arrays.toString(ints));
        assertArrayEquals(new int[]{3, 2, 1}, ints);
    }

    @Test
    void test_reverseSelf4() {
        int[] ints = {1, 2, 3, 4};
        NDArrPosition.reverseSelf(ints);
        Log.info(Arrays.toString(ints));
        assertArrayEquals(new int[]{4, 3, 2, 1}, ints);
    }

    @Test
    void test_reverseSelf5() {
        int[] ints = {1, 2, 3, 4, 5};
        NDArrPosition.reverseSelf(ints);
        Log.info(Arrays.toString(ints));
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, ints);
    }
}