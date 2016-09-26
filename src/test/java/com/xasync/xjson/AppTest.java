package com.xasync.xjson;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static junit.framework.TestCase.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Test
    public void testApp()
    {
        String root=AppTest.class.getResource("/").getPath();
        assertTrue( root!=null&&root.length()>0);
        log.info("RootPath:"+root);

        File file=new File(root+App.CONF_FILE);
        if(file.exists()){
            log.info("user config file["+(root+App.CONF_FILE)+"]");
        }else{
            log.warn("can't find config file["+(root+App.CONF_FILE)+"]");
        }
    }
}
