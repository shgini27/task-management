package org.ttweb.taskmanagement.domain.application;

import org.ttweb.taskmanagement.domain.model.activity.Activity;

public interface ActivityService {
    /**
     * Save an activity
     *
     * @param activity the activity instance
     */
    void saveActivity(Activity activity);
}
