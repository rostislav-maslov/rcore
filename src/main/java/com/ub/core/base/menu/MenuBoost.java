package com.ub.core.base.menu;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.*;

public class MenuBoost {

    protected static final String BASE_PACKAGE = "com.ub";
    protected static List<CoreMenu> menu = null;

    synchronized
    public static Map<String, CoreMenu> getExcludeMenu() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        Map<String, CoreMenu> result = new HashMap<String, CoreMenu>();
        scanner.addIncludeFilter(new AssignableTypeFilter(ExcludeCoreMenu.class));

        for (BeanDefinition bd : scanner.findCandidateComponents(BASE_PACKAGE)) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                ExcludeCoreMenu mainMenu = (ExcludeCoreMenu) aClass.newInstance();
                List<CoreMenu> coreMenus = mainMenu.getMenu();
                if (coreMenus == null) continue;
                for (CoreMenu coreMenu : coreMenus) {
                    result.put(coreMenu.getId(), coreMenu);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ignore) {}
        }

        // TODO: удалить и отказаться от хардкода в сканах
        for (BeanDefinition bd : scanner.findCandidateComponents("tech")) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                ExcludeCoreMenu mainMenu = (ExcludeCoreMenu) aClass.newInstance();
                List<CoreMenu> coreMenus = mainMenu.getMenu();
                if (coreMenus == null) continue;
                for (CoreMenu coreMenu : coreMenus) {
                    result.put(coreMenu.getId(), coreMenu);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ignore) {}
        }

        return result;
    }

    synchronized
    public static void generateMenu() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        Set<CoreMenu> menuSet = new HashSet<CoreMenu>();
        List<CoreMenu> result = new ArrayList<CoreMenu>();

        Map<String, CoreMenu> exMenu = getExcludeMenu();

        scanner.addIncludeFilter(new AssignableTypeFilter(CoreMenu.class));
        for (BeanDefinition bd : scanner.findCandidateComponents(BASE_PACKAGE)) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                CoreMenu mainMenu = (CoreMenu) aClass.newInstance();
                if (exMenu.get(mainMenu.getId()) != null)
                    continue;
                if (mainMenu.getParent() == null)
                    result.add(mainMenu);
                menuSet.add(mainMenu);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ignore) {}
        }

        for (BeanDefinition bd : scanner.findCandidateComponents("tech")) {
            try {
                Class aClass = Class.forName(bd.getBeanClassName());
                CoreMenu mainMenu = (CoreMenu) aClass.newInstance();
                if (exMenu.get(mainMenu.getId()) != null)
                    continue;
                if (mainMenu.getParent() == null)
                    result.add(mainMenu);
                menuSet.add(mainMenu);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ignore) {}
        }

        for (CoreMenu coreMenu : result) {
            coreMenu.setChild(getChildMenu(coreMenu, menuSet));
        }

        menu = sortMenu(result);
    }

    private static List<CoreMenu> getChildMenu(CoreMenu coreMenu, Set<CoreMenu> set) {
        List<CoreMenu> subMenu = new ArrayList<CoreMenu>();
        for (CoreMenu scm : set) {
            if (scm.getParent() != null && scm.getParent().getId().equals(coreMenu.getId())) {
                scm.setChild(getChildMenu(scm, set));
                subMenu.add(scm);
            }
        }

        return sortMenu(subMenu);
    }

    private static List<CoreMenu> sortMenu(List<CoreMenu> menuList) {
        Collections.sort(menuList, new Comparator<CoreMenu>() {
            @Override
            public int compare(CoreMenu menu1, CoreMenu menu2) {
                int position1 = menu1.position;
                int position2 = menu2.position;
                if (position1 > position2) {
                    return 1;
                } else if (position1 < position2) {
                    return -1;
                } else {
                    return menu1.getName().compareTo(menu2.getName());
                }
            }
        });

        return menuList;
    }

    public static List<CoreMenu> allMenu() {
        if (menu == null)
            generateMenu();
        return menu;
    }
}
