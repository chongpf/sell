/*
 * Created by Michael
 * 17-10-6 下午4:16
 */

package sell.demo.util;
import java.util.Random;

public class KeyUtil {

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer a = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(a);
    }
}
