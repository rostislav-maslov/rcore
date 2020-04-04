package com.rcore.domain.role.port.impl.javaclassfinder;

import java.io.File;
import java.io.FileFilter;

 class ExtensionMatchFileFilter implements FileFilter {

    private String extension;

    public ExtensionMatchFileFilter(String extension) {
        this.extension = extension;
    }

    public boolean accept(File file) {
        if (this.extension == null) {
            return true;
        }
        return  file.getName().endsWith("." + extension);
    }


}
