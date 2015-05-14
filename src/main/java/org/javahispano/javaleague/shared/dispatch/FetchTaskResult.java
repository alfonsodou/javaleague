package org.javahispano.javaleague.shared.dispatch;

import org.javahispano.javaleague.shared.domain.Task;
import com.gwtplatform.dispatch.rpc.shared.Result;

public class FetchTaskResult implements Result {
    private Task task;

    public FetchTaskResult() {
    }

    public FetchTaskResult(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
