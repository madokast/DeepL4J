package madokast.deepl4j.study.gradient;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.study.gradient.operator.Add;
import madokast.deepl4j.study.gradient.operator.Multiple;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class DoubleValueWithGradientTest {

    @Test
    public void test_add() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        x.setValue(2);

        DoubleValueWithGradient xAddX = Add.INS.apply(x, x);

        Log.info("x + x = " + xAddX.getValue());
    }

    @Test
    public void test_add2() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        x.setValue(2);

        DoubleValueWithGradient xAddX = Add.INS.apply(x, x);

        Log.info("g = " + xAddX.gradient(x));
    }

    @Test
    public void test_mul() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        x.setValue(20);

        DoubleValueWithGradient xMulX = Multiple.INS.apply(x, x);

        Log.info("x * x = " + xMulX.getValue());
    }

    @Test
    public void test_mul2() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        x.setValue(20);

        DoubleValueWithGradient xMulX = Multiple.INS.apply(x, x);

        Log.info("g = " + xMulX.gradient(x));
    }

    @Test
    public void test() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        DoubleValueWithGradient y = DoubleValueWithGradient.of("y");

        Add add = Add.INS;

        Multiple mul = Multiple.INS;

        DoubleValueWithGradient x2 = mul.apply(x, x);

        DoubleValueWithGradient x2y = mul.apply(x2, y);

        DoubleValueWithGradient x2yAddX = add.apply(x2y, x);

        Log.info(x2yAddX);

        x.setValue(3);
        y.setValue(4);

        Log.info(x2yAddX.getValue());
        Log.info(x2yAddX.gradient(x));
        Log.info(x2yAddX.gradient(y));
    }



}