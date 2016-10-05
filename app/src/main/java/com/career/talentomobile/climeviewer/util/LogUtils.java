package com.career.talentomobile.climeviewer.util;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Utils class to avoid Logcat logs when JUnit tests are running.
 */
public class LogUtils {

    private static Boolean running;

    public static void d(String tag, String message) {
        if (!testsRunning()) {
            Log.d(tag, message);
        }
    }

    private static boolean testsRunning() {
        if (running != null) {
            return running;
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<StackTraceElement> list = Arrays.asList(stackTrace);

        for (StackTraceElement element : list) {
            if (element.getClassName().startsWith("org.junit.")) {
                running = true;
                return running;
            }
        }

        running = false;
        return running;
    }
}
