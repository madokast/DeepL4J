package madokast.deepl4j.nd.regression;


import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.func.BinaryFloatFunction;
import madokast.deepl4j.nd.func.TernaryFloatFunction;
import org.junit.jupiter.api.Test;

/**
 * 回归问题解决第一代
 */
public class ProblemSolveI {

    @Test
    public void test() {
        float eta = 0.1f;
        float[] xs = {1, 2, 3, 4};
        float[] ys = {0.5f, 1.1f, 1.5f, 2.1f};
        float w0 = 0;
        float b0 = 0;

        TernaryFloatFunction fun = (w, b, x) -> w * x + b;
        TernaryFloatFunction fun_dw = (w, b, x) -> x;
        TernaryFloatFunction fun_db = (w, b, x) -> 1;

        float last_loss = loss(xs, ys, w0, b0, fun) * 10;

        for (int i = 0; i < 50; i++) {
            float loss = loss(xs, ys, w0, b0, fun);
            float loss_dw = loss_dw(xs, ys, w0, b0, fun, fun_dw);
            float loss_db = loss_db(xs, ys, w0, b0, fun, fun_db);

            if (last_loss <= loss) {
                eta /= 1.8;
            }
            float loss_down = last_loss - loss;
            if (loss_down < 1e-5) break;
            last_loss = loss;

            float dw = -eta * loss_dw;
            float db = -eta * loss_db;

            Log.info("w = " + w0 + ", b = " + b0 + ", loss = " + loss + ", 降低 " + loss_down + ", eta = " + eta);


            w0 += dw;
            b0 += db;

        }
    }

    private float loss_db(float[] xs, float[] ys, float w, float b,
                          TernaryFloatFunction fun, TernaryFloatFunction fun_db) {
        float loss_db = 0f;
        for (int i = 0; i < xs.length; i++) {
            float y = fun.call(w, b, xs[i]);
            float y0 = ys[i];
            loss_db += 2 * (y - y0) * fun_db.call(w, b, xs[i]);
        }

        return loss_db / xs.length;
    }


    private float loss_dw(float[] xs, float[] ys, float w, float b,
                          TernaryFloatFunction fun, TernaryFloatFunction fun_dw) {
        float loss_dw = 0f;
        for (int i = 0; i < xs.length; i++) {
            float y = fun.call(w, b, xs[i]);
            float y0 = ys[i];
            loss_dw += 2 * (y - y0) * fun_dw.call(w, b, xs[i]);
        }

        return loss_dw / xs.length;
    }

    private float loss(float[] xs, float[] ys, float w, float b, TernaryFloatFunction fun) {
        float loss = 0f;
        for (int i = 0; i < xs.length; i++) {
            float y = fun.call(w, b, xs[i]);
            float y0 = ys[i];
            loss += (y - y0) * (y - y0);
        }

        return loss / xs.length;
    }

}
