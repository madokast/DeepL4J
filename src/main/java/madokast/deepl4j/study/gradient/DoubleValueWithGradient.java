package madokast.deepl4j.study.gradient;


import java.util.Objects;

public abstract class DoubleValueWithGradient implements OperatorTree {

    private final String identifier;


    public DoubleValueWithGradient(String identifier) {
        this.identifier = identifier;
    }

    public abstract double gradient(DoubleValueWithGradient value);

    public abstract double getValue();


    public static DoubleValueWithGradient of(String identifier) {

        return new DoubleValueWithGradient(identifier) {

            private double value;

            @Override
            public double gradient(DoubleValueWithGradient value) {
                return this.equals(value) ? 1 : 0;
            }

            @Override
            public DoubleValueWithGradient[] children() {
                return new DoubleValueWithGradient[0];
            }

            @Override
            public double getValue() {
                return value;
            }

            @Override
            public void setValue(double value) {
                this.value = value;
            }
        };

    }

    @Override
    public abstract DoubleValueWithGradient[] children();

    /*--------------------------------------- getter setter -------------------------------------*/

    public String getIdentifier() {
        return identifier;
    }

    public void setValue(double value) {
        throw new UnsupportedOperationException(identifier + " 不能赋值");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleValueWithGradient that = (DoubleValueWithGradient) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return identifier;
    }
}
