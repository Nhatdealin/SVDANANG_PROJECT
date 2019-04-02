package com.diendan.svdanang.api;

import com.diendan.svdanang.tasks.BaseTask;


/**
 * Created by dcmen on 08/31/16.
 */
public interface ApiListener<Output> {
    /**
     * CallBack which is raised when api task start to execute
     * Usually, showLoading indicator is shown here
     */
    void onConnectionOpen(BaseTask task);

    /**
     * Api return result with successful = true
     * Method should handle and express data on UI
     * Remember hide the showLoading to prevent showLoading forever
     *
     * @param data
     */
    void onConnectionSuccess(BaseTask task, Output data);

    /**
     * Connection fail or result with successful = false
     * Method should show showAlert dialog to user that notify what happened
     * Remember hide the showLoading to prevent showLoading forever
     *
     * @param exception
     */
    void onConnectionError(BaseTask task, Exception exception);
}
