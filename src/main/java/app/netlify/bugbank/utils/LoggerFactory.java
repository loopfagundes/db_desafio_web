package app.netlify.bugbank.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerFactory {
    private static final Logger logger = Logger.getLogger(LoggerFactory.class.getName());

    public static void log_WARNING(String msg) {
        logger.log(Level.WARNING, msg);
    }
}