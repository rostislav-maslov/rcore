package com.rcore.domain.access.entity;

import lombok.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Access {

    protected String id = this.getClass().getSimpleName();
    protected String title = "";
    protected String description = "";

//    public static Access getInstance(Class<? extends Access> roleClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        return (Access) roleClass.getDeclaredConstructors()[0].newInstance();
//    }


    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Access)) return false;

        Access that = (Access) o;

        return this.id.equals(that.getId());
    }


}
