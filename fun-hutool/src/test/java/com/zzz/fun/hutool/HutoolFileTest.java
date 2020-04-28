package com.zzz.fun.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import cn.hutool.setting.dialect.Props;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class HutoolFileTest {

    @Test
    public void testRead() {
        FileReader fileReader = new FileReader("app.properties");
        String result = fileReader.readString();
        Console.log(result);
    }

    @Test
    public void testReadResource() throws IOException {
        ClassPathResource resource = new ClassPathResource("app.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        Console.log("Properties: {}", properties);
    }


    @Test
    public void testSetting() {
        //读取classpath下的XXX.setting，不使用变量
        Setting setting = new Setting("example.setting");

//读取classpath下的config目录下的XXX.setting，不使用变量
//        setting = new Setting("config/XXX.setting");

//读取绝对路径文件/home/looly/XXX.setting（没有就创建，关于touc请查阅FileUtil）
//第二个参数为自定义的编码，请保持与Setting文件的编码一致
//第三个参数为是否使用变量，如果为true，则配置文件中的每个key都以被之后的条目中的value引用形式为 ${key}
//        setting = new Setting(FileUtil.touc("/home/looly/XXX.setting"), CharsetUtil.CHARSET_UTF_8, true);

//读取与SettingDemo.class文件同包下的XXX.setting
//        setting = new Setting("XXX.setting", SettingDemo.class,CharsetUtil.CHARSET_UTF_8, true);

        //获取key为name的值
        setting.getStr("name");
//获取分组为group下key为name的值
        setting.getByGroup("name", "group1");
//当获取的值为空（null或者空白字符时，包括多个空格），返回默认值
        setting.getStr("name", "默认值");
//完整的带有key、分组和默认值的获得值得方法
        setting.getStr("name", "group1", "默认值");

//如果想获得其它类型的值，可以调用相应的getXXX方法，参数相似

//有时候需要在key对应value不存在的时候（没有这项设置的时候）告知户，故有此方法打印一个debug日志
        setting.getWithLog("name");
        setting.getByGroupWithLog("name", "group1");

//获取分组下所有配置键值对，组成新的Setting
        setting.getSetting("group1");

        //重新读取配置文件
//        setting.reload();

//在配置文件变更时自动加载
        setting.autoLoad(true);

//当通过代码加入新的键值对的时候，调用store会保存到文件，但是会盖原来的文件，并丢失注释
        setting.set("name1", "value");
        setting.store("/home/looly/XXX.setting");
//获得所有分组名
        setting.getGroups();

//将key-value映射为对象，原理是原理是调用对象对应的setXX方法
//        UserVO userVo = new UserVo();
//        setting.toBean(userVo);

//设定变量名的正则表达式。
//Setting的变量替换是通过正则查找替换的，如果Setting中的变量名其他冲突，可以改变变量的定义方式
//整个正则匹配变量名，分组1匹配key的名字
        setting.setVarRegex("\\$\\{(.*?)\\}");
    }



    @Test
    public void testProps() {
        Props props = new Props("app.properties");
        String user = props.getProperty("user");
        String driver = props.getStr("driver");
        String test = props.getStr("test");
        Console.log(test);
    }

}
