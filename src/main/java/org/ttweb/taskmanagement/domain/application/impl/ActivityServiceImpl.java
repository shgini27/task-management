package org.ttweb.taskmanagement.domain.application.impl;

import org.springframework.stereotype.Service;
import org.ttweb.taskmanagement.domain.application.ActivityService;
import org.ttweb.taskmanagement.domain.model.activity.Activity;
import org.ttweb.taskmanagement.domain.model.activity.ActivityRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }
}
