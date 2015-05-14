package org.javahispano.javaleague.shared.dispatch;

import org.javahispano.javaleague.shared.dto.CurrentUserDto;
import com.gwtplatform.dispatch.rpc.shared.Result;

public class FetchCurrentUserResult implements Result {
    private CurrentUserDto currentUser;

    public FetchCurrentUserResult() {
    }

    public FetchCurrentUserResult(CurrentUserDto currentUser) {
        this.currentUser = currentUser;
    }

    public CurrentUserDto getCurrentUser() {
        return currentUser;
    }
}
