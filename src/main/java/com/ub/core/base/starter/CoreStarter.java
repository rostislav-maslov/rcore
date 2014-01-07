package com.ub.core.base.starter;

import com.ub.core.base.starter.exceptions.StarterAlreadyInitException;
import com.ub.core.base.starter.exceptions.StarterNameIsNullException;

import java.util.ArrayList;
import java.util.List;

public class CoreStarter {
    protected static List<String> starts = new ArrayList<String>();

    public static synchronized void addStarter(String name) throws StarterAlreadyInitException, StarterNameIsNullException {
        if(name == null)
            throw new StarterNameIsNullException();
        for (String str : starts) {
            if (str.equals(name)) {
                throw new StarterAlreadyInitException(name);
            }
        }
        starts.add(name);
    }
}
