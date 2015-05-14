package org.javahispano.javaleague.server.guice;

import org.javahispano.javaleague.server.authentication.AuthenticationModule;
import org.javahispano.javaleague.server.dispatch.DispatchHandlersModule;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        install(new DispatchHandlersModule());
        install(new AuthenticationModule());
    }
}
