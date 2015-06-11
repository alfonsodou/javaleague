/**
 * 
 */
package org.javahispano.javaleague.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;

/**
 * @author adou
 *
 */
public class JavaleagueEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {
        ScriptInjector.fromString(JavaleagueClientBundle.INSTANCE.prettify().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .inject();
    }
}
