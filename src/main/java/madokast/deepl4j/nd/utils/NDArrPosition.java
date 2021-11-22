package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.utils.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 用于定位
 *
 * @author ZhaoRX
 * @since 2021/11/21 23:50
 */
public class NDArrPosition {

    public static int[] sizes(int[] shape) {
        int[] sizes = new int[shape.length];
        sizes[shape.length - 1] = shape[shape.length - 1];
        for (int i = shape.length - 2; i >= 0; i--) {
            sizes[i] = sizes[i + 1] * shape[i];
        }
        return sizes;
    }

    public static int pos(final int[] shape, final int[] sizes, final int[] coordinate) {
        Assert.require(coordinate.length == shape.length, "坐标维度不对");
        int pos = 0;
        for (int i = 0; i < coordinate.length; i++) {
            int temp = coordinate[i] * (i == coordinate.length - 1 ? 1 : sizes[i + 1]);
            pos += temp;
        }
        return pos;
    }

    public static int[] reverse(int[] coo) {
        int[] copy = Arrays.copyOf(coo, coo.length);
        reverseSelf(copy);
        return copy;
    }

    public static void reverseSelf(int[] coo) {
        int mid = coo.length / 2;
        for (int i = 0; i < mid; i++) {
            int left = coo[i];
            int right = coo[coo.length - i - 1];

            coo[i] = right;
            coo[coo.length - i - 1] = left;
        }
    }
}
