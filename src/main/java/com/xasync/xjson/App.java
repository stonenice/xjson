package com.xasync.xjson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{

    public final static String CONF_FILE="xjson.properties";

    private static Logger log= LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        String root=App.class.getResource("/").getPath();
        String configFile=root+App.CONF_FILE;
        File file=new File(configFile);
        Properties p=new Properties();
        if(file.exists()){
            try {
                InputStream is=new FileInputStream(configFile);
                p.load(is);
            } catch (FileNotFoundException e) {
                log.error("Config File["+configFile+"] .MSG:"+e.getMessage());
            } catch (IOException e) {
                log.error("can't load the config file["+configFile+"]. MSG:"+e.getMessage());
            }
        }else{
            p.setProperty("version","1.0");
        }
        log.info("xjson:"+p.getProperty("version"));
        System.out.println( "xjson v1.0" );
    }
}
