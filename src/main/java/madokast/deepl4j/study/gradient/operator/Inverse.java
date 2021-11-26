package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.study.gradient.DoubleValueWithGradient;

/**
 * 倒数
 */
public class Inverse implements UnaryOperator {

    public static Inverse INS = new Inverse();

    @Override
    public DoubleValueWithGradient apply(DoubleValueWithGradient a) {
        return new DoubleValueWithGradient("(" + a.getIdentifier() + ")^-1") {
            @Override
            public double gradient(DoubleValueWithGradient value) {
                return -1. / (a.getValue() * a.getValue()) * a.gradient(value);
            }

            @Override
            public double getValue() {
                return 1. / a.getValue();
            }

            @Override
            public DoubleValueWithGradient[] children() {
                return new DoubleValueWithGradient[]{a};
            }
        };
    }

    @Override
    public String identifier() {
        return "^-1";
    }

    private Inverse() {
    }
}
