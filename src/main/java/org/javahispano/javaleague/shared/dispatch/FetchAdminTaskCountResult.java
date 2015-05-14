package org.javahispano.javaleague.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;

public class FetchAdminTaskCountResult implements Result {
    private Integer totalTasks;

    public FetchAdminTaskCountResult() {
    }

    public FetchAdminTaskCountResult(Integer totalTasks) {
        this.totalTasks = totalTasks;
    }

    public Integer getTotalTasksCount() {
        return totalTasks;
    }
}
