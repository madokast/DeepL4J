package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.study.gradient.DoubleValueWithGradient;

public interface UnaryOperator extends Operator{

    DoubleValueWithGradient apply(DoubleValueWithGradient a);

    @Override
    String identifier();
}
