package com.xasync.xjson.fastjson;

import com.alibaba.fastjson.JSON;
import com.xasync.xjson.model.UserModel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by dell on 2016-09-25.
 */
public class FeatureTest {
    private Logger log= LoggerFactory.getLogger(this.getClass());
    @Test
    public void testBean2Str(){
        UserModel user=new UserModel();
        user.setId(1);
        user.setUserName("stone");
        user.setPassword("123");
        user.setAddress("green street NO3.");
        user.setBirthday(new Date());
        user.setEmail("stonenice@outlook.com");
        user.setMarried(false);
        user.setPhone(13567575432L);
        user.setSalary(10000.00);
        user.setSex("man");
        user.setEnabled(true);

        log.info(JSON.toJSONString(user));
    }
}
