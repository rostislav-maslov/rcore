package com.ub.core.file;

public class FileRoutes {
    public final static String ROOT = "/admin/file/";
    public final static String ADD = ROOT +"add";
    public final static String LIST = ROOT +"list";
    public final static String DELETE = ROOT +"delete";
    public final static String GET_FILE_FATH_VAR = "file";
    public final static String GET_FILE = "/files/{"+GET_FILE_FATH_VAR+"}";

    public static String GET_FILE(String fileId) {return "/file/"+fileId;}
}
