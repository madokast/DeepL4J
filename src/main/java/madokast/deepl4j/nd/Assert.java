package madokast.deepl4j.nd;

/**
 * Assert
 *
 * @author ZhaoRX
 * @since 2021/11/21 22:35
 */
public class Assert {

    public static void require(boolean b, String msg) {
        if (!b) {
            throw new RuntimeException(msg);
        }
    }

}
