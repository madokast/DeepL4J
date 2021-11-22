package madokast.deepl4j.nd.utils;

import madokast.deepl4j.nd.NDArr;
import sun.misc.Unsafe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 工厂
 */
public class NDArrFactory {

    public static NDArr array(float... array) {
        Assert.require(array.length > 0, "数组长度大于 0");
        return NDArr.of(array);
    }

    /**
     * 全 0 矩阵
     *
     * @param shape 维度
     * @return 全 0 矩阵
     */
    public static NDArr zeros(int... shape) {
        Assert.require(shape.length > 0, "维度大于 0");
        int size = 1;
        for (int d : shape) {
            Assert.require(d > 0, "每维大于 0");
            size *= d;
        }
        float[] data = new float[size];
        return new NDArr(data, shape);
    }

    public static NDArr zerosLike(NDArr arr) {
        return zeros(arr.shape());
    }

    /**
     * 未初始化的数组（模拟）
     *
     * @param shape 维度
     * @return 未初始化的数组
     */
    public static NDArr empty(int... shape) {
        NDArr zeros = zeros(shape);
        float[] data = zeros.data();
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = Float.intBitsToFloat(random.nextInt());
        }
        return zeros;
    }

    /**
     * 单位矩阵
     *
     * @param size 方阵大小
     * @return 单位矩阵
     */
    public static NDArr eye(int size) {
        NDArr zeros = zeros(size, size);
        for (int i = 0; i < size; i++) {
            zeros.set(new int[]{i, i}, 1.0f);
        }
        return zeros;
    }

    /**
     * 全 1 矩阵
     *
     * @param shape 维度
     * @return 全 1 矩阵
     */
    public static NDArr ones(int... shape) {
        NDArr zeros = zeros(shape);
        float[] data = zeros.data();
        Arrays.fill(data, 1.0f);
        return zeros;
    }

    public static NDArr full(float val, int... shape) {
        NDArr zeros = zeros(shape);
        float[] data = zeros.data();
        Arrays.fill(data, val);
        return zeros;
    }

    public static NDArr linSpace(float start, float end, int number) {
        Assert.require(number >= 2, "number 大于等于 2");
        float span = end - start;
        float delta = span / (number - 1);
        float[] data = new float[number];
        for (int i = 0; i < number; i++) {
            data[i] = start + delta * i;
        }
        data[number - 1] = end;
        return NDArr.of(data);
    }


    public static NDArr range(int start, int end, int step) {
        Assert.require((end - start) * step > 0, "参数非法");
        List<Float> data = new ArrayList<>();
        int i = 0;
        while (start + step * i < end) {
            data.add((float) (start + step * i));
            i++;
        }
        int size = data.size();
        float[] t = new float[size];
        for (int j = 0; j < t.length; j++) {
            t[j] = data.get(j);
        }
        return NDArr.of(t);
    }

    public static NDArr range(int start, int end) {
        return range(start, end, 1);
    }

    public static NDArr range(int end) {
        return range(0, end, 1);
    }

}
