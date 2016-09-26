package com.xasync.xjson.annotation;

/**
 * Created by dell on 2016-09-26.
 */
public @interface XJSON {
    String alias() default "";
    boolean hide() default false;
    String mapping() default "";
    boolean unmap() default false;
    String formart() default "";
    String column() default "";
    boolean iscolumn() default true;

}
