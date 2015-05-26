package org.javahispano.javaleague.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "org.javahispano.javaleague.javaleague";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}