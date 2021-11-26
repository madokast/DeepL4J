package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.study.gradient.DoubleValueWithGradient;

public class ReLU implements UnaryOperator {

    public static ReLU INS = new ReLU();

    @Override
    public DoubleValueWithGradient apply(DoubleValueWithGradient a) {
        return new DoubleValueWithGradient("ReLU(" + a.getIdentifier() + ")") {
            @Override
            public double gradient(DoubleValueWithGradient value) {
                return a.getValue() <= 0 ? 0 : a.gradient(value);
            }

            @Override
            public double getValue() {
                return Math.max(0., a.getValue());
            }

            @Override
            public DoubleValueWithGradient[] children() {
                return new DoubleValueWithGradient[]{a};
            }
        };
    }

    @Override
    public String identifier() {
        return "ReLU";
    }

    private ReLU(){}
}
