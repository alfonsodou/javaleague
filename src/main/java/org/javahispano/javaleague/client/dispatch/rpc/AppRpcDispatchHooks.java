/*
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.javahispano.javaleague.client.dispatch.rpc;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.gwtplatform.dispatch.rpc.client.RpcDispatchHooks;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.Result;

public class AppRpcDispatchHooks implements RpcDispatchHooks {
    private static final Logger logger = Logger.getLogger(AppRpcDispatchHooks.class.getName());

    @Override
    public <R extends Result> void onExecute(Action<R> action, boolean undo) {
        logger.log(Level.INFO, "Executing rpc dispatch " + action.getServiceName()
                + " resource action (undo: " + String.valueOf(undo) + ")");
    }

    @Override
    public <R extends Result> void onSuccess(Action<R> action, R result, boolean undo) {
        logger.log(Level.INFO, "Successfully executed " + action.getServiceName()
                + (" undo: " + String.valueOf(undo) + ")"));
    }

    @Override
    public <R extends Result> void onFailure(Action<R> action, Throwable caught, boolean undo) {
        logger.log(Level.INFO, "Failed to executed " + action.getServiceName() + " "
                + caught.getMessage() + (" undo: " + String.valueOf(undo) + ")"));
    }
}
