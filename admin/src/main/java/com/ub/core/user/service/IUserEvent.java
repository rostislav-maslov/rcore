package com.ub.core.user.service;

import com.ub.core.user.models.UserDoc;

/**
 * Created by maslov on 03.11.15.
 */
public interface IUserEvent {
    public void preSave(UserDoc userDoc);
    public void afterSave(UserDoc userDoc);

    public Boolean preDelete(UserDoc userDoc);
    public void afterDelete(UserDoc userDoc);
}
