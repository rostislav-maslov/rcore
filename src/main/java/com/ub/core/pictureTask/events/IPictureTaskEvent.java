package com.ub.core.pictureTask.events;

import com.ub.core.pictureTask.models.PictureTaskDoc;

public interface IPictureTaskEvent {
    public void preSave(PictureTaskDoc pictureTaskDoc);
    public void afterSave(PictureTaskDoc pictureTaskDoc);

    public Boolean preDelete(PictureTaskDoc pictureTaskDoc);
    public void afterDelete(PictureTaskDoc pictureTaskDoc);
}
