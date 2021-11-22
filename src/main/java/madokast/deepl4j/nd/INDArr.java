package madokast.deepl4j.nd;

import madokast.deepl4j.nd.tools.FloatFormat;
import madokast.deepl4j.nd.tools.NDCoordinateIter;
import madokast.deepl4j.nd.utils.Assert;
import madokast.deepl4j.nd.utils.NDArrFactory;
import madokast.deepl4j.nd.utils.NDArrPosition;

public interface INDArr {

    float at(final int[] coordinate);

    void set(int[] coordinate, float val);

    int size();

    int[] shape();

    INDArr reShape(int... shape);

    /**
     * 坐标迭代器
     */
    NDCoordinateIter coordinateIter();

    /**
     * @return 维度
     */
    default int dimensionNumber() {
        return shape().length;
    }

    default float broadcastAt(final int[] coordinate) {
        int dim = dimensionNumber();
        int[] coo = new int[dim];
        System.arraycopy(coordinate, coordinate.length - dim, coo, 0, dim);
        int[] shape = shape();
        for (int i = 0; i < coo.length; i++) {
            if (shape[i] == 1) coo[i] = 0;
            else Assert.require(coo[i] < shape[i], "无法广播");
        }
        return at(coo);
    }

    /**
     * @return 转置（和原 NDArr 无关）
     */
    default NDArr T() {
        NDArr t = NDArrFactory.zeros(NDArrPosition.reverse(this.shape()));
        for (int[] coo : this.coordinateIter()) {
            t.set(NDArrPosition.reverse(coo), at(coo));
        }
        return t;
    }

    /**
     * @return 转置视图
     */
    default INDArr TView() {

        INDArr assist = this;

        return new INDArr() {

            @Override
            public float at(int[] coordinate) {
                return assist.at(NDArrPosition.reverse(coordinate));
            }

            @Override
            public void set(int[] coordinate, float val) {
                throw new UnsupportedOperationException("转置视图不能 set");
            }

            @Override
            public int size() {
                return assist.size();
            }

            @Override
            public int[] shape() {
                return NDArrPosition.reverse(assist.shape());
            }

            @Override
            public INDArr reShape(int... shape) {
                throw new UnsupportedOperationException("转置视图不能 reShape");
            }

            @Override
            public NDCoordinateIter coordinateIter() {
                return new NDCoordinateIter(shape());
            }
        };
    }


    default String toString(FloatFormat format) {
        int[] shape = this.shape();
        int[] sizes = NDArrPosition.sizes(shape);
        NDCoordinateIter iter = coordinateIter();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shape.length; i++) {
            sb.append("[");
        }

        int right = 0;
        int i = 0;
        for (int[] coordinate : iter) {
            float v = at(coordinate);
            sb.append(format.format(v));

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

            i++;
        }

        return sb.substring(0, sb.length() - right - 1);
    }
}
