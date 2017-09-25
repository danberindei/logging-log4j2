package org.apache.logging.log4j.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check the runtime Java version.
 */
public class JavaVersion {
    private static int[] PARSED_VERSION = parseVersion(System.getProperty("java.version"));

    static int[] parseVersion(String property) {
        if (property.startsWith("1.")) {
            // 1.7.0, 1.8.0_101
            Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(?:_(\\d+))?(-.*)?");
            Matcher matcher = pattern.matcher(property);
            if (matcher.matches()) {
                return new int[]{
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0,
                };
            }
        } else {
            // 9, 9-ea, 9.1, 9.1.2
            Pattern pattern = Pattern.compile("(\\d+)(?:\\.(\\d+))?(?:\\.(\\d+))?(-.*)?");
            Matcher matcher = pattern.matcher(property);
            if (matcher.matches()) {
                return new int[]{
                        Integer.parseInt(matcher.group(1)),
                        matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0,
                        0,
                        matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0,
                };
            }
        }

        return null;
    }

    /**
     * Check if the Java version is greater or equal to the version specified by the parameters.
     * <p>
     * Prior to Java 9, the major version is 1.
     * In Java 9, the major version is 9, the micro version is always 0, and the update version is the security level.
     */
    public static boolean atLeast(int major, int minor, int micro, int update) {
        if (PARSED_VERSION == null)
            return false;

        if (major != PARSED_VERSION[0])
            return major < PARSED_VERSION[0];
        if (minor != PARSED_VERSION[1])
            return minor < PARSED_VERSION[1];
        if (micro != PARSED_VERSION[2])
            return micro < PARSED_VERSION[2];
        return update <= PARSED_VERSION[3];
    }
}
