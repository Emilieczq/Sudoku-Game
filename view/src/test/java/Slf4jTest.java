/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zhenqi
 */
public class Slf4jTest {


    @Test
    public void test() {
        Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
        logger.trace("Hello World");
        logger.debug("Hello World");
        logger.info("Hello World");
        logger.warn("Hello World");
        logger.error("Hello World");
    }
}
