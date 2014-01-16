package com.ub.core.base.menu;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuBoost {

    protected static final String BASE_PACKAGE = "com.ub";
    protected static List<CoreMenu> menu = null;

    synchronized
    public static void generateMenu() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        Set<CoreMenu> menuSet = new HashSet<CoreMenu>();
        List<CoreMenu> result = new ArrayList<CoreMenu>();

        scanner.addIncludeFilter(new AssignableTypeFilter(CoreMenu.class));
        for (BeanDefinition bd : scanner.findCandidateComponents(BASE_PACKAGE)) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                CoreMenu mainMenu = (CoreMenu) aClass.newInstance();
                if(mainMenu.getParent() == null)
                    result.add(mainMenu);
                menuSet.add(mainMenu);
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }

        for(CoreMenu coreMenu : result){
            coreMenu.setChild(getChildMenu(coreMenu, menuSet));
            for(CoreMenu scm : coreMenu.getChild()){
                scm.setChild(getChildMenu(scm, menuSet));
            }
        }

        menu = result;
    }

    private static List<CoreMenu> getChildMenu(CoreMenu coreMenu, Set<CoreMenu> set){
        ArrayList<CoreMenu> subMenu = new ArrayList<CoreMenu>();
        for(CoreMenu scm : set){
            if(scm.getParent()!=null && scm.getParent().getId().equals(coreMenu.getId())){
                subMenu.add(scm);
            }
        }
        return subMenu;
    }

    public static List<CoreMenu> allMenu() {
        if (menu == null)
            generateMenu();
        return menu;
    }
}
