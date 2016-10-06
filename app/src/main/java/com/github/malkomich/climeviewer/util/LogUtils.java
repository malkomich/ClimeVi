package com.github.malkomich.climeviewer.util;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Utils class to avoid Logcat logs when JUnit tests are running.
 */
public class LogUtils {

    private static final String PACKAGE_PREFIX = "org.junit.";

    private static Boolean running;

    public static void d(String tag, String message) {
        if (!testsRunning()) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (!testsRunning()) {
            Log.i(tag, message);
        }
    }

    private static boolean testsRunning() {
        if (running != null) {
            return running;
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<StackTraceElement> list = Arrays.asList(stackTrace);

        for (StackTraceElement element : list) {
            if (element.getClassName().startsWith(PACKAGE_PREFIX)) {
                running = true;
                return running;
            }
        }

        running = false;
        return false;
    }

}
