package madokast.deepl4j.nd;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Log
 *
 * @author ZhaoRX
 * @since 2021/11/21 23:16
 */
public class Log {

    public static void info(Object msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[2];
        System.out.println(
                LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).substring(0, 12) +
                        " [" + limit(stackTraceElement.getClassName(), 40) + "] " +
                        msg.toString()
        );
    }

    private static String limit(String className, int maxLength) {
        String[] clsArr = className.split("\\.");
        int length = clsArr.length * 2 - 1;
        int abbrNumber = clsArr.length;
        int expend = clsArr.length - 1;
        while (length < maxLength && expend >= 0) {
            length += clsArr[expend].length() - 1;
            expend--;
            abbrNumber--;
        }

        if (length > maxLength) abbrNumber++;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < abbrNumber; i++) {
            sb.append(clsArr[i].charAt(0)).append('.');
        }
        for (; i < clsArr.length; i++) {
            sb.append(clsArr[i]).append('.');
        }
        sb.deleteCharAt(sb.length() - 1);
        return String.format("%" + "" + "s", sb);
    }

}
