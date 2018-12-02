package com.ub.core.pictureTask.starter;

import com.ub.core.pictureTask.services.PictureResizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PictureTaskExecutor {

    @Autowired private PictureResizeService pictureResizeService;

    @Scheduled(fixedDelay = 60 * 60 * 1000, initialDelay = 60 * 60 * 1000)
    public void runNextTask() {
        try {
            pictureResizeService.runResizeForNextTask();
        } catch (Exception e) {

        }
    }

}
