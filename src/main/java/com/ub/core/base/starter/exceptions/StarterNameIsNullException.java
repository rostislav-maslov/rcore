package com.ub.core.base.starter.exceptions;

public class StarterNameIsNullException  extends Exception{
    protected static String temp = " Starter name is null.";
    public StarterNameIsNullException(){
        super(temp);
    }
}
