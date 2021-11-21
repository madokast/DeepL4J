package madokast.deepl4j.nd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * numpy 多维数组 float 格式
 *
 * @author ZhaoRX
 * @since 2021/11/21 22:29
 */
public class NDArr implements Serializable {

    private final float[] data;

    private final int[] shape;

    /**
     * sizes[i] = sizes[i-1] * shape[i]
     */
    private final int[] sizes;

    public float at(int[] coordinate) {
        return data[NDArrPosition.pos(shape, sizes, coordinate)];
    }

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

        int[] sizes = new int[shape.length];
        sizes[shape.length - 1] = shape[shape.length - 1];
        for (int i = shape.length - 2; i >= 0; i--) {
            sizes[i] = sizes[i + 1] * shape[i];
        }

        this.data = data;
        this.shape = shape;
        this.sizes = sizes;
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
     * @return 维度
     */
    public int dimensionNumber() {
        return shape.length;
    }

    /**
     * @return 元素数目
     */
    public int size() {
        return data.length;
    }

    public int[] shape() {
        return shape;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shape.length; i++) {
            sb.append("[");
        }
        int right = 0;
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);

            right = 0;

            for (int size : sizes) {
                if ((i + 1) % size == 0) {
                    sb.append("]");
                    right++;
                }
            }

            if (right > 1) sb.append("\r\n");
            else sb.append(" ");
            for (int ignore = 0; ignore < right; ignore++) {
                sb.append("[");
            }

        }

        return sb.substring(0, sb.length() - right - 1);
    }

    /*---------------------------------- view ----------------------------------*/
}
