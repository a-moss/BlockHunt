package com.mydeblob.blockhunt.commandutil;

import java.lang.reflect.Method;

/**
 * @author Mydeblob
 * @since 2/23/15
 */
public class Data {

    private Command cmd;
    private Class<?> clazz;
    private Method method;


    public Data(Command cmd, Class<?> clazz, Method method){
        this.cmd = cmd;
        this.clazz = clazz;
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Command getCmd() {
        return cmd;
    }

    public Method getMethod() {
        return method;
    }

}
