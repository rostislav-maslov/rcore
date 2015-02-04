package com.ub.core.role.service;

import com.ub.core.base.role.Role;
import com.ub.core.base.starter.ACoreStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleBoost extends ACoreStarter {

    @Autowired private RoleService roleService;

    protected static final String BASE_PACKAGE = "*";
    //protected static final String BASE_PACKAGE = "com.ub";

    @Override
    protected void onStart() {
        this.generateMenu();
    }


    protected void generateMenu() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        List<Role> result = new ArrayList<Role>();

        scanner.addIncludeFilter(new AssignableTypeFilter(Role.class));

        Set<BeanDefinition> bds = scanner.findCandidateComponents("com.ub");
        //bds.addAll(scanner.findCandidateComponents("ru"));
        //bds.addAll(scanner.findCandidateComponents("org"));

        for (BeanDefinition bd : bds) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());

                Role mainMenu = (Role) aClass.newInstance();

                Role role = new Role();
                if(role.getId().equals(mainMenu.getId())) continue;

                result.add(mainMenu);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for(Role role : result){
            roleService.save(role);
        }

        System.gc();
    }


}
