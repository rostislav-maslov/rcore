package com.ub.core.pictureTask.starter;

import com.ub.core.picture.services.PictureService;
import com.ub.core.pictureTask.models.PictureTaskDoc;
import com.ub.core.pictureTask.services.PictureResizeService;
import com.ub.core.pictureTask.services.PictureTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PictureTaskExecutor {

    @Autowired private PictureResizeService pictureResizeService;

    @Scheduled(fixedDelay = 900)
    public void runNextTask() {
        try {
            pictureResizeService.runResizeForNextTask();
        } catch (Exception e) {

        }
    }

}
