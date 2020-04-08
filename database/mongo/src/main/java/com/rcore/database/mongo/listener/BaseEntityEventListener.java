package com.rcore.database.mongo.listener;

import com.rcore.domain.base.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseEntityEventListener extends AbstractMongoEventListener<BaseEntity> {

    /**
     * Обновляем updateAt
     * @param event
     */
    @Override
    public void onBeforeSave(BeforeSaveEvent<BaseEntity> event) {
        super.onBeforeSave(event);
        BaseEntity entity = event.getSource();
        entity.setUpdatedAt(new Date());
    }

}
