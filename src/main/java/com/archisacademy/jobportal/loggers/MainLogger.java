package com.archisacademy.jobportal.loggers;

import com.archisacademy.jobportal.exceptions.JobPortalServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;


public class MainLogger {
    private final Logger logger;

    public MainLogger(Class<?> clz){
        this.logger= LogManager.getLogger(clz);
    }

    public void log(String message){
        logger.info(message);
    }

    public void logError(String message){
        logger.error(message);
    }

    public void logInfo(String message){
        logger.info(message);
    }

    public void log(String message, HttpStatus statusCode){ // 404
        logger.error(message);
        throw new JobPortalServerException(message,statusCode);
    }
}
