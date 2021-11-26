package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.nd.Log;
import madokast.deepl4j.nd.utils.Assert;
import madokast.deepl4j.study.gradient.DoubleValueWithGradient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReLUTest {

    @Test
    public void test() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        DoubleValueWithGradient y = DoubleValueWithGradient.of("y");
        DoubleValueWithGradient z = DoubleValueWithGradient.of("z");

        z.setValue(3);

        DoubleValueWithGradient x2 = Multiple.INS.apply(x, x);
        DoubleValueWithGradient x2y = Multiple.INS.apply(x2, y);
        DoubleValueWithGradient x2yy = Add.INS.apply(x2y, y);
        DoubleValueWithGradient x2yyz = Add.INS.apply(x2yy, z);

        x.setValue(2);
        y.setValue(4);

        Log.info(x2yyz);
        assertEquals(2. * 2 * 4 + 4 + 3, x2yyz.getValue());
    }

    @Test
    public void test1() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        DoubleValueWithGradient y = DoubleValueWithGradient.of("y");
        DoubleValueWithGradient z = DoubleValueWithGradient.of("z");

        z.setValue(3);

        DoubleValueWithGradient x2 = Multiple.INS.apply(x, x);
        DoubleValueWithGradient x2y = Multiple.INS.apply(x2, y);
        DoubleValueWithGradient x2yy = Add.INS.apply(x2y, y);
        DoubleValueWithGradient x2yyz = Add.INS.apply(x2yy, z);

        x.setValue(2);
        y.setValue(-4);

        DoubleValueWithGradient r = ReLU.INS.apply(x2yyz);

        Log.info(x2yyz);
        assertEquals(-2. * 2 * 4 - 4 + 3, x2yyz.getValue());

        Log.info(r);
        assertEquals(0, r.getValue());
    }

    @Test
    public void test2() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        DoubleValueWithGradient y = DoubleValueWithGradient.of("y");
        DoubleValueWithGradient z = DoubleValueWithGradient.of("z");

        z.setValue(3);

        DoubleValueWithGradient x2 = Multiple.INS.apply(x, x);
        DoubleValueWithGradient x2y = Multiple.INS.apply(x2, y);
        DoubleValueWithGradient x2yy = Add.INS.apply(x2y, y);
        DoubleValueWithGradient x2yyz = Add.INS.apply(x2yy, z);

        x.setValue(2);
        y.setValue(-4);

        DoubleValueWithGradient r = ReLU.INS.apply(x2yyz);

        assertEquals(0., r.gradient(x));
        assertEquals(0., r.gradient(y));
        assertEquals(0., r.gradient(z));
    }

    @Test
    public void test3() {
        DoubleValueWithGradient x = DoubleValueWithGradient.of("x");
        DoubleValueWithGradient y = DoubleValueWithGradient.of("y");
        DoubleValueWithGradient z = DoubleValueWithGradient.of("z");

        z.setValue(3);

        DoubleValueWithGradient x2 = Multiple.INS.apply(x, x);
        DoubleValueWithGradient x2y = Multiple.INS.apply(x2, y);
        DoubleValueWithGradient x2yy = Add.INS.apply(x2y, y);
        DoubleValueWithGradient x2yyz = Add.INS.apply(x2yy, z);

        x.setValue(2);
        y.setValue(4);

        DoubleValueWithGradient r = ReLU.INS.apply(x2yyz);

        assertEquals(2 * x.getValue() * y.getValue(), r.gradient(x));
        assertEquals(x2.getValue() + 1., r.gradient(y));
        assertEquals(1., r.gradient(z));
    }
}