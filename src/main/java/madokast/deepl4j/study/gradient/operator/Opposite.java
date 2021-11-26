package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.study.gradient.DoubleValueWithGradient;

/**
 * 相反数
 */
public class Opposite implements UnaryOperator {

    public static Opposite INS = new Opposite();

    @Override
    public DoubleValueWithGradient apply(DoubleValueWithGradient a) {
        return new DoubleValueWithGradient("-(" + a.getIdentifier() + ")") {
            @Override
            public double gradient(DoubleValueWithGradient value) {
                return -a.gradient(value);
            }

            @Override
            public double getValue() {
                return -a.getValue();
            }

            @Override
            public DoubleValueWithGradient[] children() {
                return new DoubleValueWithGradient[]{a};
            }
        };
    }

    @Override
    public String identifier() {
        return "-";
    }

    private Opposite() {
    }
}
