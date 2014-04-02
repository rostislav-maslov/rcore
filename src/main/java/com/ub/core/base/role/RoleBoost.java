package com.ub.core.base.role;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.*;

public class RoleBoost {

    protected static final String BASE_PACKAGE = "com.ub";
    protected static List<Role> roles = null;


    synchronized
    protected static void generateMenu() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        List<Role> result = new ArrayList<Role>();

        scanner.addIncludeFilter(new AssignableTypeFilter(Role.class));
        for (BeanDefinition bd : scanner.findCandidateComponents(BASE_PACKAGE)) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                Role mainMenu = (Role) aClass.newInstance();
                result.add(mainMenu);
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }

        roles = result;
        System.gc();
    }

    public static List<Role> allRoles() {
        if (roles == null)
            generateMenu();
        return roles;
    }
}
