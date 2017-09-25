package org.apache.logging.log4j.core.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test the java.version system property parsing.
 */
public class JavaVersionTest {
    @Test
    public void testParseVersion() {
        assertArrayEquals(new int[]{1, 7, 0, 0}, JavaVersion.parseVersion("1.7.0"));
        assertArrayEquals(new int[]{1, 8, 0, 102}, JavaVersion.parseVersion("1.8.0_102"));
        assertArrayEquals(new int[]{9, 0, 0, 0}, JavaVersion.parseVersion("9"));
        assertArrayEquals(new int[]{9, 0, 0, 0}, JavaVersion.parseVersion("9-ea"));
        assertArrayEquals(new int[]{9, 0, 0, 1}, JavaVersion.parseVersion("9.0.1"));
        assertArrayEquals(new int[]{9, 1, 0, 0}, JavaVersion.parseVersion("9.1"));
        assertArrayEquals(new int[]{9, 1, 0, 2}, JavaVersion.parseVersion("9.1.2"));
    }
}
