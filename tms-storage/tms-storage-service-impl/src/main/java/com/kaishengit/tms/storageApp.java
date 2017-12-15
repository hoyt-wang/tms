package com.kaishengit.tms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author fankay
 */
public class storageApp {

    private static Logger logger = LoggerFactory.getLogger(storageApp.class);

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");
        context.start();

        logger.info("TMS-System-Service start success....");

        System.in.read();
    }

}
