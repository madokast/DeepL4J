package madokast.deepl4j.nd;

import madokast.deepl4j.nd.tools.NDCoordinateIter;
import madokast.deepl4j.nd.utils.Assert;
import madokast.deepl4j.nd.utils.NDArrFactory;
import madokast.deepl4j.nd.utils.NDArrPosition;
import sun.security.provider.SHA;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * numpy 多维数组 float 格式
 *
 * @author ZhaoRX
 * @since 2021/11/21 22:29
 */
public class NDArr implements INDArr, Serializable {

    private final float[] data;

    /**
     * 仅被 reShape 改引用
     */
    private int[] shape;

    /**
     * sizes[i] = sizes[i-1] * shape[i]
     * 仅被 reShape 改引用
     */
    private int[] sizes;

    @Override
    public float at(final int[] coordinate) {
        return data[NDArrPosition.pos(shape, sizes, coordinate)];
    }

    @Override
    public void set(int[] coordinate, float val) {
        data[NDArrPosition.pos(shape, sizes, coordinate)] = val;
    }

    /*---------------------------------- constructor ----------------------------*/

    public NDArr(float[] data, int[] shape) {
        Assert.require(data.length > 0, "NDArr can not be empty");

        int size = 1;
        for (int dim : shape) {
            size *= dim;
        }

        Assert.require(data.length == size, "NDArr size and shape are not match");

        this.data = data;
        this.shape = shape;
        this.sizes = NDArrPosition.sizes(shape);
    }

    private NDArr(float[] data, int[] shape, int[] sizes) {
        this.data = data;
        this.shape = shape;
        this.sizes = sizes;
    }

    public static NDArr of(float val) {
        return of(new float[]{val});
    }

    public static NDArr of(float[] arr) {
        return new NDArr(Arrays.copyOf(arr, arr.length), new int[]{arr.length});
    }

    public static NDArr of(float[][] arr) {
        int r = arr.length;
        Assert.require(r > 0, "行数大于0");
        int c = arr[0].length;
        Assert.require(c > 0, "列数大于0");
        int[] shape = new int[]{r, c};
        float[] data = new float[r * c];
        for (int i = 0; i < r; i++) {
            System.arraycopy(arr[i], 0, data, i * c, c);
        }
        return new NDArr(data, shape);
    }

    public static NDArr of(float[][][] arr) {
        int h = arr.length;
        Assert.require(h > 0, "高度大于0");
        int r = arr[0].length;
        Assert.require(r > 0, "行数大于0");
        int c = arr[0][0].length;
        Assert.require(c > 0, "列数大于0");
        int[] shape = new int[]{h, r, c};
        float[] data = new float[h * r * c];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < r; j++) {
                System.arraycopy(arr[i][j], 0, data, i * r * c + j * c, c);
            }
        }
        return new NDArr(data, shape);
    }

    /*---------------------------------- view ----------------------------------*/

    /**
     * @return 元素数目
     */
    @Override
    public int size() {
        return data().length;
    }

    @Override
    public int[] shape() {
        return shape;
    }

    /**
     * @param shape 改变形状
     * @return this
     */
    @Override
    public NDArr reShape(int... shape) {
        int size = 1;
        for (int dim : shape) {
            size *= dim;
        }

        Assert.require(data().length == size, "NDArr size and shape are not match");

        this.shape = shape;
        this.sizes = NDArrPosition.sizes(shape);

        return this;
    }

    /**
     * @return 暴露 data
     */
    public float[] data() {
        return data;
    }

    /**
     * @return 展平，同 data()
     */
    public float[] flat() {
        return data();
    }

    public String toString() {
        return toString(String::valueOf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NDArr ndArr = (NDArr) o;
        return Arrays.equals(data, ndArr.data) &&
                Arrays.equals(shape, ndArr.shape) &&
                Arrays.equals(sizes, ndArr.sizes);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + Arrays.hashCode(shape);
        result = 31 * result + Arrays.hashCode(sizes);
        return result;
    }

    /*---------------------------------- others ----------------------------------*/

    public NDArr copy() {
        float[] dataC = Arrays.copyOf(data, data.length);
        int[] shapeC = Arrays.copyOf(shape, shape.length);
        int[] sizesC = Arrays.copyOf(sizes, sizes.length);

        return new NDArr(dataC, shapeC, sizesC);
    }

    @Override
    public NDCoordinateIter coordinateIter() {
        return new NDCoordinateIter(shape());
    }
}
