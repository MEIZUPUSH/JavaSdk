package com.meizu.push.sdk.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * User: jasperxgwang
 * Date: 2018-3-7 16:17
 */
public class Logging {

    private static Logger logger = null;

    private Logging() {
    }

    public static Logger getLogger(String name) {
        if (null == logger) {
            InputStream is = Logging.class.getClass().getResourceAsStream("/flyme-push-logger.properties");
            try {
                LogManager.getLogManager().readConfiguration(is);
            } catch (Exception e) {
                logging.warning("input properties file is error.\n" + e.toString());
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    logging.warning("close FileInputStream a case.\n" + e.toString());
                }
            }

            logger = Logger.getLogger(name);
        }
        return logger;
    }

    private static Logger logging = Logger.getLogger(Logging.class.getName());
}
