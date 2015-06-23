package org.javahispano.javaleague.server.guice;

import org.javahispano.javaleague.server.dispatch.LoginUserHandler;
import org.javahispano.javaleague.shared.dispatch.LoginUserAction;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
    	bindHandler(LoginUserAction.class, LoginUserHandler.class);
    }
}