package madokast.deepl4j.nd.tools;

@FunctionalInterface
public interface FloatFormat {

    String format(float f);

    FloatFormat NOOP = String::valueOf;
}
