package com.zzz.fun;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApolloTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApolloTest.class);

    @Before
    public void before() throws IOException, JoranException {
        String filePath = ApolloTest.class.getClassLoader().getResource("logback.xml").getPath();
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        File externalConfigFile = new File(filePath);
        if (!externalConfigFile.exists() || !externalConfigFile.isFile() || !externalConfigFile.canRead()) {
            throw new IOException("Logback External Config File Parameter does not reference a file that exists");
        }
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        configurator.doConfigure(filePath);
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        System.setProperty("apollo.refreshInterval","10");
    }

    @Test
    public void test() throws InterruptedException {
        while (true) {
            String key1 = ConfigService.getAppConfig().getProperty("timeout", "someDefaultValueForTheKey");
            LOGGER.info("key1:{}", key1);
            String key2 = ConfigService.getConfig("zzz").getProperty("aaa", "someDefaultValueForTheKey");
            LOGGER.info("key2:{}", key2);
            String key3 = ConfigService.getConfig("zzz").getProperty("bbb", "someDefaultValueForTheKey");
            LOGGER.info("key3:{}", key3);
            String key4 = ConfigService.getConfig("sss").getProperty("aaa", "someDefaultValueForTheKey");
            LOGGER.info("key4:{}\n\n\n", key4);
            TimeUnit.SECONDS.sleep(5L);
        }
    }

    @Test
    public void test01() {
        String key1 = ConfigService.getConfig("TEST1.zzz").getProperty("1111", "someDefaultValueForTheKey");
        LOGGER.info("key1:{}", key1);
        String key2 = ConfigService.getConfig("zzz").getProperty("1111", "someDefaultValueForTheKey");
        LOGGER.info("key2:{}", key2);
        String key3 = ConfigService.getConfig("TEST1.sss").getProperty("msg", "someDefaultValueForTheKey");
        LOGGER.info("key3:{}", key3);

    }

    @Test
    public void test0() {
        String key1 = ConfigService.getAppConfig().getProperty("timeout", "someDefaultValueForTheKey");
        LOGGER.info("key1:{}", key1);
    }


    @Test
    public void test1() {
        ConfigFile configFile = ConfigService.getConfigFile("xml", ConfigFileFormat.XML);
        String key1 = configFile.getContent();
        LOGGER.info("key1:{}", key1);
    }

    @Test
    public void test2() {
        String key1 = ConfigService.getConfig("yml.yml").getProperty("debug", "someDefaultValueForTheKey");
        LOGGER.info("key1:{}", key1);
    }

    @Test
    public void test3() {
        ConfigFile configFile = ConfigService.getConfigFile("json", ConfigFileFormat.JSON);
        String key1 = configFile.getContent();
        LOGGER.info("key1:{}", key1);
    }


}
