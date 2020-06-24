package com.zzz.fun.hutool;

import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

public class HutoolMailTest {

    @Test
    public void testMail() {
        MailUtil.sendText("zhangsitian915369813@foxmail.com", "测试邮件", "我的测试case");
    }

}
