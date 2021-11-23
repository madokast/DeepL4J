package madokast.deepl4j.study.gradient.operator;

import madokast.deepl4j.study.gradient.DoubleValueWithGradient;

public interface BiOperator extends Operator{

    DoubleValueWithGradient apply(DoubleValueWithGradient a, DoubleValueWithGradient b);

    @Override
    String identifier();
}
