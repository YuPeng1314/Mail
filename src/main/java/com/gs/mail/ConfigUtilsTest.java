package com.gs.mail;

import org.junit.Test;

import java.util.Properties;

/**
 * Created by Administrator on 2017/9/22.
 */
public class ConfigUtilsTest {

    @Test
    public void testBuild() {
        Properties properties = ConfigUtils.build("src/main/resources/mail.properties");
        System.out.println(ConfigUtils.getString("username"));
        System.out.println(ConfigUtils.getInteger("mail.smtp.port"));
    }

}
