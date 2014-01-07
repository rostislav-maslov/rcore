package com.ub.core.base.starter.exceptions;

public class StarterAlreadyInitException extends Exception{
    protected static String temp = " - Starter alredy init.";
    public StarterAlreadyInitException(String message){
        super(message + temp);
    }
}
