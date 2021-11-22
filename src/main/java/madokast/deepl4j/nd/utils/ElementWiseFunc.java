package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.NDArr;

import java.util.Map;

/**
 * element wise 函数
 *
 * @author ZhaoRX
 * @since 2021/11/21 23:55
 */
public class ElementWiseFunc {

    public static NDArr add(NDArr a, NDArr b) {
        return elementWiseApply(a, b, Float::sum);
    }

    public static NDArr add(NDArr a, float b) {
        return elementWiseApply(a, b, Float::sum);
    }

    public static NDArr subtract(NDArr a, NDArr b) {
        return elementWiseApply(a, b, (v1, v2) -> v1 - v2);
    }

    public static NDArr subtract(NDArr a, float b) {
        return elementWiseApply(a, b, (v1, v2) -> v1 - v2);
    }

    public static NDArr multiply(NDArr a, NDArr b) {
        return elementWiseApply(a, b, (v1, v2) -> v1 * v2);
    }

    public static NDArr multiply(NDArr a, float b) {
        return elementWiseApply(a, b, (v1, v2) -> v1 * v2);
    }

    public static NDArr divide(NDArr a, NDArr b) {
        return elementWiseApply(a, b, (v1, v2) -> v1 / v2);
    }

    public static NDArr divide(NDArr a, float b) {
        return elementWiseApply(a, b, (v1, v2) -> v1 / v2);
    }

    public static NDArr sin(NDArr a) {
        return elementWiseApply(a, v -> (float) Math.sin(v));
    }

    public static NDArr cos(NDArr a) {
        return elementWiseApply(a, v -> (float) Math.cos(v));
    }

    public static NDArr tan(NDArr a) {
        return elementWiseApply(a, v -> (float) Math.tan(v));
    }

    public static NDArr elementWiseApply(NDArr left, NDArr right, FloatBinaryFun fun) {
        int da = left.dimensionNumber();
        int db = right.dimensionNumber();
        NDArr big, small;
        if (da >= db) {
            big = left;
            small = right;
        } else {
            big = right;
            small = left;
        }

        NDArr ret = NDArrFactory.zerosLike(big);
        for (int[] coordinate : ret.coordinateIter()) {
            float bigVal = big.at(coordinate);
            float smallVal = small.broadcastAt(coordinate);
            ret.set(coordinate, fun.call(bigVal, smallVal));
        }
        return ret;
    }

    public static NDArr elementWiseApply(NDArr left, float right, FloatBinaryFun fun) {
        NDArr ret = NDArrFactory.zerosLike(left);
        float[] data = ret.data();
        float[] dataLeft = left.data();

        for (int i = 0; i < data.length; i++) {
            data[i] = fun.call(dataLeft[i], right);
        }

        return ret;
    }

    public static NDArr elementWiseApply(NDArr left, FloatUnaryFun fun) {
        NDArr ret = NDArrFactory.zerosLike(left);
        float[] data = ret.data();
        float[] dataLeft = left.data();

        for (int i = 0; i < data.length; i++) {
            data[i] = fun.call(dataLeft[i]);
        }

        return ret;
    }


    private interface FloatBinaryFun {
        float call(float left, float right);
    }

    private interface FloatUnaryFun {
        float call(float val);
    }
}
