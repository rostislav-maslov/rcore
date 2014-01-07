package com.ub.core.base.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassMapping<FROM, TO> {
    private Class fromClass;
    private Class toClass;
    private Map<Field, Field> map = new HashMap<Field, Field>();

    public ClassMapping(Class<FROM> f, Class<TO> t) {
        fromClass = f;
        toClass = t;
        Field[] ffields = fromClass.getDeclaredFields();
        Field[] tfields = toClass.getDeclaredFields();
        for (Field ffield : ffields) {
            for (Field tfield : tfields) {
                if (!ffield.getName().equals(tfield.getName()))
                    continue;
                if (!ffield.getType().equals(tfield.getType()))
                    continue;
                map.put(ffield, tfield);

            }
        }
    }

    public void mapping(FROM fv, TO tv) {
        for(Field f : map.keySet()){
            Field t = map.get(f);
            try {
                f.setAccessible(true);
                t.setAccessible(true);
                t.set(tv, f.get(fv));
                f.setAccessible(false);
                t.setAccessible(false);
            } catch (Exception e) {
            }
        }
    }
}
