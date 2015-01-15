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
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
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
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }

        for (CoreMenu coreMenu : result) {
            coreMenu.setChild(getChildMenu(coreMenu, menuSet));
            for (CoreMenu scm : coreMenu.getChild()) {
                scm.setChild(getChildMenu(scm, menuSet));
            }
        }

        Collections.sort(result, new Comparator<CoreMenu>() {
            @Override
            public int compare(CoreMenu menu1, CoreMenu menu2) {
                int position1 = menu1.position;
                int position2 = menu2.position;
                if (position1 > position2) {
                    return 1;
                } else if (position1 < position2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        menu = result;
    }

    private static List<CoreMenu> getChildMenu(CoreMenu coreMenu, Set<CoreMenu> set) {
        ArrayList<CoreMenu> subMenu = new ArrayList<CoreMenu>();
        for (CoreMenu scm : set) {
            if (scm.getParent() != null && scm.getParent().getId().equals(coreMenu.getId())) {
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
