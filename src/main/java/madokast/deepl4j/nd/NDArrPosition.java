package madokast.deepl4j.nd;

/**
 * 用于定位
 *
 * @author ZhaoRX
 * @since 2021/11/21 23:50
 */
public class NDArrPosition {

    public static int pos(int[] shape, int[] sizes, int[] coordinate) {
        Assert.require(coordinate.length == shape.length, "坐标维度不对");
        int pos = 0;
        for (int i = 0; i < coordinate.length; i++) {
            int temp = coordinate[i] * (i == coordinate.length - 1 ? 1 : sizes[i + 1]);
            pos += temp;
        }
        return pos;
    }

}
