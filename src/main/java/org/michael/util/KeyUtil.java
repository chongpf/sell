/*
 * Created by Michael
 * 17-10-7 下午3:31
 */

/*
 * Created by Michael
 * 17-10-6 下午4:16
 */

package org.michael.util;

import java.util.Random;

public class KeyUtil {

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int count = random.nextInt(900000) + 100000;

        return String.valueOf(System.currentTimeMillis()) + count;
    }
}
