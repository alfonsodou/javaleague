package org.javahispano.javaleague.server.dispatch;

import javax.inject.Inject;

import org.javahispano.javaleague.server.dao.TaskDao;
import org.javahispano.javaleague.shared.dispatch.FetchTaskAction;
import org.javahispano.javaleague.shared.dispatch.FetchTaskResult;
import org.javahispano.javaleague.shared.domain.Task;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class FetchTaskHandler extends AbstractAction<FetchTaskAction, FetchTaskResult> {
    private final TaskDao taskDao;

    @Inject
    FetchTaskHandler(TaskDao taskDao) {
        super(FetchTaskAction.class);

        this.taskDao = taskDao;
    }

    @Override
    public FetchTaskResult execute(FetchTaskAction action, ExecutionContext context) throws ActionException {
        Task task = taskDao.get(action.getTaskId());

        return new FetchTaskResult(task);
    }
}
