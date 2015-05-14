package org.javahispano.javaleague.server.dispatch;

import org.javahispano.javaleague.server.dispatch.validators.AdminActionValidator;
import org.javahispano.javaleague.shared.dispatch.FetchAdminTaskCountAction;
import org.javahispano.javaleague.shared.dispatch.FetchCurrentUserAction;
import org.javahispano.javaleague.shared.dispatch.FetchTaskAction;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class DispatchHandlersModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(FetchTaskAction.class, FetchTaskHandler.class);
        bindHandler(FetchCurrentUserAction.class, FetchCurrentUserHandler.class);

        // This fetch has a Validator which only lets App Admins fetch it.
        bindHandler(FetchAdminTaskCountAction.class, FetchAdminTaskCountHandler.class, AdminActionValidator.class);
    }
}
