package com.ub.core.base.menu;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.List;

public class MenuBoost {

    protected static final String BASE_PACKAGE = "com.ub";
    protected static List<MainMenu> menu = null;

    synchronized
    public static void generateMenu() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        List<MainMenu> mainMenus = new ArrayList<MainMenu>();

        scanner.addIncludeFilter(new AssignableTypeFilter(MainMenu.class));
        for (BeanDefinition bd : scanner.findCandidateComponents(BASE_PACKAGE)) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                MainMenu mainMenu = (MainMenu) aClass.newInstance();
                mainMenus.add(mainMenu);
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }

        menu = mainMenus;
    }

    public static List<MainMenu> allMenu() {
        if (menu == null)
            generateMenu();
        return menu;
    }
}
