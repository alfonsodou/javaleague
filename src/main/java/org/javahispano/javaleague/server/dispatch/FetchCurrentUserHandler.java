package org.javahispano.javaleague.server.dispatch;

import javax.inject.Inject;

import org.javahispano.javaleague.server.authentication.CurrentUserDtoProvider;
import org.javahispano.javaleague.shared.dispatch.FetchCurrentUserAction;
import org.javahispano.javaleague.shared.dispatch.FetchCurrentUserResult;
import org.javahispano.javaleague.shared.dto.CurrentUserDto;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class FetchCurrentUserHandler extends AbstractAction<FetchCurrentUserAction, FetchCurrentUserResult> {
    private CurrentUserDtoProvider currentUserDtoProvider;

    @Inject
    FetchCurrentUserHandler(CurrentUserDtoProvider currentUserDtoProvider) {
        super(FetchCurrentUserAction.class);

        this.currentUserDtoProvider = currentUserDtoProvider;
    }

    @Override
    public FetchCurrentUserResult execute(FetchCurrentUserAction action, ExecutionContext context)
            throws ActionException {
        CurrentUserDto currentUser = currentUserDtoProvider.get();

        return new FetchCurrentUserResult(currentUser);
    }
}
