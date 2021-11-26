package madokast.deepl4j.study.gradient;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.study.gradient.operator.Add;
import madokast.deepl4j.study.gradient.operator.Inverse;
import madokast.deepl4j.study.gradient.operator.Multiple;
import madokast.deepl4j.study.gradient.operator.Opposite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 一步起飞，飞起来了
 */
class RegressionTest {

    @Test
    public void test() {
        float lr = 0.1f;
        float[] xs = {1, 2, 3, 4};
        float[] ys = {0.5f, 1.1f, 1.5f, 2.1f};
        float w0 = 0;
        float b0 = 0;
        int epochNumber = 10;

        // 转为可计算梯度的量
        DoubleValueWithGradient[] xsGrad = new DoubleValueWithGradient[xs.length];
        DoubleValueWithGradient[] ysGrad = new DoubleValueWithGradient[ys.length];
        for (int i = 0; i < xs.length; i++) {
            xsGrad[i] = DoubleValueWithGradient.of("x" + i);
            xsGrad[i].setValue(xs[i]);

            ysGrad[i] = DoubleValueWithGradient.of("y" + i);
            ysGrad[i].setValue(ys[i]);
        }

        DoubleValueWithGradient wGrad = DoubleValueWithGradient.of("w");
        DoubleValueWithGradient bGrad = DoubleValueWithGradient.of("b");
        DoubleValueWithGradient length = DoubleValueWithGradient.of("length");
        wGrad.setValue(w0);
        bGrad.setValue(b0);
        length.setValue(xs.length);

        // 定义 loss
        DoubleValueWithGradient loss = DoubleValueWithGradient.of("0");
        loss.setValue(0);
        for (int i = 0; i < xs.length; i++) {
            DoubleValueWithGradient yi = Add.INS.apply(
                    Multiple.INS.apply(xsGrad[i], wGrad),
                    bGrad);
            DoubleValueWithGradient diff = Add.INS.apply(
                    ysGrad[i],
                    Opposite.INS.apply(yi)
            );
            loss = Add.INS.apply(
                    loss,
                    Multiple.INS.apply(diff, diff)
            );
        }
        loss = Multiple.INS.apply(
                loss,
                Inverse.INS.apply(length)
        );

        Log.info("loss = " + loss);

        double last_loss = loss.getValue() * 10;

        for (int i = 0; i < epochNumber; i++) {
            double lossValue = loss.getValue();
            double wG = loss.gradient(wGrad);
            double bG = loss.gradient(bGrad);

            if (last_loss <= lossValue) lr /= 1.8;

            double loss_down = last_loss - lossValue;
            if (loss_down < 1e-5) break;

            last_loss = lossValue;

            double dw = -lr * wG;
            double db = -lr * bG;

            double w = wGrad.getValue();
            double b = bGrad.getValue();

            Log.info("w = " + w + ", b = " + b + ", loss = " + lossValue + ", 降低 " + loss_down + ", eta = " + lr);

            wGrad.setValue(w + dw);
            bGrad.setValue(b + db);
        }
    }

}