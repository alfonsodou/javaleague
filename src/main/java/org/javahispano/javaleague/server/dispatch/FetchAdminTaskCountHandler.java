package org.javahispano.javaleague.server.dispatch;

import javax.inject.Inject;

import org.javahispano.javaleague.server.dao.TaskDao;
import org.javahispano.javaleague.shared.dispatch.FetchAdminTaskCountAction;
import org.javahispano.javaleague.shared.dispatch.FetchAdminTaskCountResult;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class FetchAdminTaskCountHandler extends AbstractAction<FetchAdminTaskCountAction, FetchAdminTaskCountResult> {
    private final TaskDao taskDao;

    @Inject
    FetchAdminTaskCountHandler(TaskDao taskDao) {
        super(FetchAdminTaskCountAction.class);

        this.taskDao = taskDao;
    }

    @Override
    public FetchAdminTaskCountResult execute(FetchAdminTaskCountAction action, ExecutionContext context)
            throws ActionException {
        Integer totalCount = taskDao.findTotalCount();

        return new FetchAdminTaskCountResult(totalCount);
    }
}
