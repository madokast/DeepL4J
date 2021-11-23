package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.study.gradient.DoubleValueWithGradient;

import java.util.List;

public class Add implements BiOperator {

    public static Add INS = new Add();

    @Override
    public DoubleValueWithGradient apply(DoubleValueWithGradient a, DoubleValueWithGradient b) {
        String id = "(" + a.getIdentifier() + " + " + b.getIdentifier() + ")";
        return new DoubleValueWithGradient(id) {
            @Override
            public double gradient(DoubleValueWithGradient value) {
                return a.gradient(value) + b.gradient(value);
            }

            @Override
            public double getValue() {
                return a.getValue() + b.getValue();
            }

            @Override
            public DoubleValueWithGradient[] children() {
                return new DoubleValueWithGradient[]{a, b};
            }
        };
    }

    @Override
    public String identifier() {
        return "+";
    }

    private Add() {
    }
}
