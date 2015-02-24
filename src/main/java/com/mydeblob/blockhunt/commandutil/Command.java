package com.mydeblob.blockhunt.commandutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mydeblob
 * @since 2/20/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String name();
    String permission();
    String usage();
    String[] aliases() default {};
    boolean allowConsole() default false;
    boolean tabCompletion() default false;
    int requiredArgs() default 0; //Assuming it's only a command with no args


}
