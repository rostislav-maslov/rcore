package com.ub.core.starter;

import com.ub.core.starter.exceptions.StarterAlreadyInitException;
import com.ub.core.starter.exceptions.StarterNameIsNullException;

public abstract class ACoreStarter {
    public void init() {
        try {
            CoreStarter.addStarter(getClass().getName());
            onStart();
        } catch (StarterNameIsNullException e) {
            e.printStackTrace();
            //TODO Добавить в лог ошибку
        } catch (StarterAlreadyInitException e) {
            e.printStackTrace();
            //TODO Добавить в лог ошибку
        } catch (Exception e) {
            e.printStackTrace();
            //TODO Добавить в лог ошибку
        }
    }

    /**
     * Метод который будет запускаться при старте приложения
     */
    protected abstract void onStart();
}
